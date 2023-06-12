package com.wms.inwms.domain.returnOrder;

import com.wms.inwms.domain.base.BaseRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;

@Repository
public interface ReturnRepository extends BaseRepo<ReturnEntity, Long> {
    Page<ReturnEntity> findByCreatedBetweenAndOrderNumContaining(Instant startDate, Instant endDate, String name, Pageable pageable);
}
