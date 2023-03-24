package com.wms.inwms.domain.receive;

import com.wms.inwms.domain.base.BaseRepo;
import com.wms.inwms.domain.base.BaseService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ReceiveService extends BaseService<Receive, Long> {
    private ReceiveRepository repository;
    public ReceiveService(ReceiveRepository receiveRepository) {
        super(receiveRepository);
        this.repository = receiveRepository;
    }
}
