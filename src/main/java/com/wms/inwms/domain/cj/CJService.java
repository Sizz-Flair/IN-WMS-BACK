package com.wms.inwms.domain.cj;

import com.wms.inwms.domain.base.BaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CJService extends BaseService<CJDto, Long> {
    private CJRepository cjRepository;

    @Autowired
    public CJService(CJRepository cjRepository) {
        super(cjRepository);
        this.cjRepository = cjRepository;
    }

    @Transactional
    public void saveAll(List<CJDto> cjDtoList) {
        this.cjRepository.saveAll(cjDtoList);
    }

    @Transactional
    public List<CJDto> findAll() {
        return this.cjRepository.findAll();
    }
}