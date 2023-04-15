package com.wms.inwms.domain.returnOrder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wms.inwms.domain.base.BaseRepo;
import com.wms.inwms.domain.base.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReturnService extends BaseService<Return, Long> {

    private final ReturnRepository returnRepository;
    private final ObjectMapper objectMapper;

    public ReturnService(ReturnRepository returnRepository, ObjectMapper objectMapper) {
        super(returnRepository);
        this.returnRepository = returnRepository;
        this.objectMapper = objectMapper;
    }

    public List<Return> findAll() {
        return this.returnRepository.findAll();
    }

    @Transactional
    public List<Return> saveAll(List<Return> returnDataList) throws SQLIntegrityConstraintViolationException{
        try {
            this.returnRepository.saveAll(returnDataList);
        } catch(SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        } finally {

        }


        return this.returnRepository.saveAll(returnDataList);
    }

    public void saveReturnOrderData(List<ReturnOrderDto> returnOrderList) {
        try{
            List<Return> returnDataList = objectMapper.convertValue(returnOrderList, ArrayList.class);
            this.returnRepository.saveAll(returnDataList);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
