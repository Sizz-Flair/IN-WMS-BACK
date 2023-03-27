package com.wms.inwms.domain.receive;

import com.wms.inwms.domain.base.BaseRepo;
import com.wms.inwms.domain.base.BaseService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class ReceiveService extends BaseService<Receive, Long> {

    private ReceiveRepository repository;

    public ReceiveService(ReceiveRepository receiveRepository) {
        super(receiveRepository);
        this.repository = receiveRepository;
    }

    @Transactional
    public List<Receive> findByCreatedBetween() throws NullPointerException {
        LocalDateTime localDateTime = LocalDateTime.now();

        Instant startDate = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Instant endDate = localDateTime.minusDays(10).atZone(ZoneId.systemDefault()).toInstant();

        return repository.findByCreatedBetween(startDate, endDate).get();
    }
}
