package com.wms.inwms.domain.inbound;

import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wms.inwms.domain.base.BaseRepo;
import com.wms.inwms.domain.base.BaseService;
import com.wms.inwms.domain.inbound.dto.InboundResultDto;
import com.wms.inwms.domain.inbound.dto.InboundSaveDto;
import com.wms.inwms.domain.location.lowerlocation.LowerLocation;
import com.wms.inwms.domain.location.upperlocation.UpperLocation;
import com.wms.inwms.util.customException.CustomRunException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.swing.undo.CannotUndoException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;
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

    public InboundService(InboundRepository repository, EntityManager entityManager) {
        super(repository);
        this.repository = repository;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public void test() {
        InboundEntity inbound = this.select().from(qInbound).where(qInbound.id.eq(1L)).fetchOne();
        System.out.println();
    }

    public List<InboundResultDto.InboundSaveResultDto> saveInboundMapping(List<InboundSaveDto> saveDto, String up, String low) throws CustomRunException{
        try{
            if (up.isEmpty() || low.isEmpty()) throw new CustomRunException("상위 또는 하위 로케이션을 지정하지 않았습니다.");

            /*캐시 서버로 변경 예정*/
//            UpperLocation upperLocation =
//            LowerLocation lowerLocation = this.queryFactory.selectFrom(qLowerLocation).where(qLowerLocation.lowLocationName.eq(low)).fetchOne();




            String mappingNum = String.valueOf(UUID.randomUUID()).replace("-", "");
            List<InboundEntity> inboundEntityList = saveDto.stream().map(e -> {
                e.setLowerLocation(lowerLocation);
                e.setUpperLocation(upperLocation.get());
                e.setMappingNUm(mappingNum);
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
                throw new DataIntegrityViolationException("");
            }
        }
    }
}
