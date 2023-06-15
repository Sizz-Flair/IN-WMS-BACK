package com.wms.inwms.domain.inbound;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wms.inwms.domain.base.BaseService;
import com.wms.inwms.domain.inbound.dto.InboundResultDto;
import com.wms.inwms.domain.inbound.dto.InboundSaveDto;
import com.wms.inwms.domain.location.lowerlocation.LowerLocation;
import com.wms.inwms.domain.location.lowerlocation.LowerLocationService;
import com.wms.inwms.domain.location.upperlocation.UpperLocation;
import com.wms.inwms.domain.location.upperlocation.UpperLocationService;
import com.wms.inwms.util.MessageUtil;
import com.wms.inwms.util.SecurityUserUtil;
import com.wms.inwms.util.customException.CustomRunException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
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

    /**
     * ==============================================
     * <p> 입고 데이터 저장
     * ==============================================
     * user : akfur
     * date : 2023-06-15
     *
     * @param saveDto
     * @param up
     * @param low
     * @return List<InboundResultDto.InboundSaveResultDto>
     */
    public List<InboundResultDto.InboundSaveResultDto> saveInboundMapping(List<InboundSaveDto> saveDto, String up, String low) {
        try {
            if (up.isEmpty() || low.isEmpty()) throw new CustomRunException(MessageUtil.message("DeSelectLocation"));

            /*캐시 서버로 변경 예정*/
            UpperLocation upperLocation = upperLocationService.findByName(up).orElseThrow(() -> new CustomRunException(MessageUtil.message("EmptyUpperLocation")));
            LowerLocation lowerLocation = lowerLocationService.findByName(low).orElseThrow(() -> new CustomRunException(MessageUtil.message("EmptyLowerLocation")));

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
            if (sqlException.getErrorCode() == 1062) {
                throw new DuplicateKeyException(MessageUtil.message("DuplicateData"));
            } else {
                throw new DataIntegrityViolationException(MessageUtil.message("IntegrityError"));
            }
        }
    }

    /**
     * ==============================================
     * <p> 검색일자로 맵핑된 입고 데이터 조회
     * ==============================================
     * user : akfur
     * date : 2023-06-15
     *
     * @param startDate
     * @param endDate
     * @return List<InboundResultDto.InboundMappingResultDto>
     */
    public List<InboundResultDto.InboundMappingResultDto> findByMappingToDate(Instant startDate, Instant endDate) {
        List<Tuple> inboundTuple = getInboundMapping(startDate, endDate).orElseThrow(() -> new CustomRunException(MessageUtil.message("EmptyValue")));
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

    /**
     * ==============================================
     * <p>맵핑정보 그룹화하여 조회
     * ==============================================
     * user : akfur
     * date : 2023-06-15
     *
     * @param startDate
     * @param endDate
     * @return Optional<List<Tuple>>
     */
    private Optional<List<Tuple>> getInboundMapping(Instant startDate, Instant endDate) {
        List<Tuple> inboundTuple = this.queryFactory.select(qInbound, qInbound.count()).from(qInbound).where(qInbound.created.between(startDate, endDate))
                .groupBy(qInbound.mappingNum).fetch();

        return Optional.of(inboundTuple);
    }

    public List<InboundResultDto.InboundSelectResultDto> getSelectInboundData(String mappingNum) {
        List<InboundEntity> inboundEntities = this.repository.findByMappingNum(mappingNum).orElseThrow(() -> new CustomRunException(MessageUtil.message("EmptyValue")));
        List<InboundResultDto.InboundSelectResultDto> resultDtos =
                inboundEntities.stream().map(e -> InboundResultDto.InboundSelectResultDto.builder().number(e.getNumber()).state(e.getState()).build()).collect(Collectors.toList());

        return resultDtos;
    }
}
