package com.wms.inwms.domain.inbound;

import com.wms.inwms.domain.base.BaseRepo;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.wms.inwms.domain.inbound
 * fileName       : InboundRepository
 * author         : akfur
 * date           : 2023-06-12
 */
@Repository
public interface InboundRepository extends BaseRepo<InboundEntity, Long> {
    List<InboundEntity> findByCreatedBetween(Instant startData, Instant endDate);
    Optional<List<InboundEntity>> findByMappingNum(String mappingNum);
}
