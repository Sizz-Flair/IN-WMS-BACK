package com.wms.inwms.domain.receive;

import com.wms.inwms.domain.base.BaseRepo;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReceiveRepository extends BaseRepo<Receive, Long> {
    Optional<List<Receive>> findByCreatedBetween(Instant startData, Instant edDate);
}