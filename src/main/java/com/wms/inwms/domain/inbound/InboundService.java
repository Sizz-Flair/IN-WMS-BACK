package com.wms.inwms.domain.inbound;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wms.inwms.domain.base.BaseService;
import com.wms.inwms.domain.inbound.dto.InboundResultDto;
import com.wms.inwms.domain.inbound.dto.InboundSaveDto;
import com.wms.inwms.domain.location.lowerlocation.LowerLocation;
import com.wms.inwms.domain.location.lowerlocation.LowerLocationService;
import com.wms.inwms.domain.location.upperlocation.UpperLocation;
import com.wms.inwms.domain.location.upperlocation.UpperLocationService;
import com.wms.inwms.util.SecurityUserUtil;
import com.wms.inwms.util.customException.CustomRunException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * packageName    : com.wms.inwms.domain.inbound
 * fileName       : InboundService
 * author         : akfur
 * date           : 2023-06-12
 */
@Service
public class InboundService extends BaseService<InboundEntity, Long> {

    private final InboundRepository repository;
    private final JPAQueryFactory queryFactory;
    private final UpperLocationService upperLocationService;
    private final LowerLocationService lowerLocationService;

    public InboundService(InboundRepository repository,
                          EntityManager entityManager,
                          UpperLocationService upperLocationService,
                          LowerLocationService lowerLocationService
    ) {
        super(repository);
        this.repository = repository;
        this.queryFactory = new JPAQueryFactory(entityManager);
        this.upperLocationService = upperLocationService;
        this.lowerLocationService = lowerLocationService;
    }

    public List<InboundResultDto.InboundSaveResultDto> saveInboundMapping(List<InboundSaveDto> saveDto, String up, String low) {
        try{
            if (up.isEmpty() || low.isEmpty()) throw new CustomRunException("상위 또는 하위 로케이션을 지정하지 않았습니다.");

            /*캐시 서버로 변경 예정*/
            UpperLocation upperLocation = upperLocationService.findByName(up).orElseThrow(() -> new CustomRunException("상위 로케이션이 없습니다"));
            LowerLocation lowerLocation = lowerLocationService.findByName(low).orElseThrow(() -> new CustomRunException("하위 로케이션이 없습니다"));

            String mappingNum = String.valueOf(UUID.randomUUID()).replace("-", "");

            /* 로그인 시 유저 정보에 있는 agent_code를 User(security) 권한 정보에 넣음 */
            String agentCode = String.valueOf(SecurityUserUtil.getUserDetailInfo().getAuthorities().toArray()[0]);

            List<InboundEntity> inboundEntityList = saveDto.stream().map(e -> {
                e.setLowerLocation(lowerLocation);
                e.setUpperLocation(upperLocation);
                e.setMappingNum(mappingNum);
                e.setAgentCode(agentCode);
                return e.convertEntity();
            }).collect(Collectors.toList());

            List<InboundEntity> inboundEntities = this.saveAll(inboundEntityList);

            return inboundEntities.stream().map(e -> e.covertResultDto()).collect(Collectors.toList());
        } catch (NullPointerException e) {
            throw new NullPointerException();
        } catch (DataIntegrityViolationException e) {
            SQLException sqlException = (SQLException) e.getRootCause();
            if(sqlException.getErrorCode() == 1062) {
                throw new DuplicateKeyException("중복 데이터가 있습니다.");
            } else {
                throw new DataIntegrityViolationException("무결성 오류 입니다.");
            }
        }
    }

    public List<InboundResultDto.InboundMappingResultDto> findByMappingToDate(Instant startDate, Instant endDate) {
        List<Tuple> inboundTuple = getInboundMapping(startDate, endDate).orElseThrow(() -> new CustomRunException("값이 없습니다"));
        List<InboundResultDto.InboundMappingResultDto> resultList = new ArrayList<>();

        for (Tuple tuple : inboundTuple) {
            InboundEntity inbound = tuple.get(0, InboundEntity.class);
            Long amount = tuple.get(1, Long.class);
            InboundResultDto.InboundMappingResultDto resultData = InboundResultDto.InboundMappingResultDto.builder()
                    .number(inbound.getNumber()).state(inbound.getState()).upperLocation(inbound.getUpperLocation().getUpLocationName())
                    .lowerLocation(inbound.getLowerLocation().getLowLocationName()).mappingNum(inbound.getMappingNum()).amount(amount).agentCode(inbound.getAgentCode())
                    .build();

            resultList.add(resultData);
        }
        return resultList;
    }

    private Optional<List<Tuple>> getInboundMapping(Instant startDate, Instant endDate) {
         List<Tuple> inboundTuple = this.queryFactory.select(qInbound, qInbound.count()).from(qInbound).where(qInbound.created.between(startDate, endDate))
                .groupBy(qInbound.mappingNum).fetch();

        return Optional.of(inboundTuple);
    }

    public List<InboundResultDto.InboundSelectResultDto> getSelectInboundData(String mappingNum) {
        List<InboundEntity> inboundEntities = this.repository.findByMappingNum(mappingNum).orElseThrow(() -> new CustomRunException("데이터가 없습니다"));
        List<InboundResultDto.InboundSelectResultDto> resultDtos =
                inboundEntities.stream().map(e -> InboundResultDto.InboundSelectResultDto.builder().number(e.getNumber()).state(e.getState()).build()).collect(Collectors.toList());

        return resultDtos;
    }
}
