package com.wms.inwms.domain.receive.receivelist;

import com.wms.inwms.domain.base.BaseService;
import com.wms.inwms.domain.receive.ReceiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiveListService extends BaseService<ReceiveList, Long> {

    private ReceiveListRepository receiveListRepository;
    @Autowired
    public ReceiveListService(ReceiveListRepository receiveListRepository) {
        super(receiveListRepository);
        this.receiveListRepository = receiveListRepository;
    }

    public void saveAll(List<ReceiveList> receiveList) {
        this.receiveListRepository.saveAll(receiveList);
    }
}
