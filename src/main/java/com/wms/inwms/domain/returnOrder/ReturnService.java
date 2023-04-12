package com.wms.inwms.domain.returnOrder;

import com.wms.inwms.domain.base.BaseRepo;
import com.wms.inwms.domain.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnService extends BaseService<Return, Long> {

    private ReturnRepository returnRepository;

    public ReturnService(ReturnRepository returnRepository) {
        super(returnRepository);
        this.returnRepository = returnRepository;
    }

    public List<Return> findAll() {
        return this.returnRepository.findAll();
    }
}
