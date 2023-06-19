package com.wms.inwms.domain.base;

import com.wms.inwms.domain.inbound.QInboundEntity;
import com.wms.inwms.domain.location.lowerlocation.QLowerLocation;
import com.wms.inwms.domain.location.upperlocation.QUpperLocation;
import com.wms.inwms.domain.returnOrder.QReturnEntity;

import java.io.Serializable;
import java.util.List;

public class BaseService<T, ID extends Serializable> extends WmsBaseService<T, ID> {

    protected BaseRepo<T, ID> repository;
    protected QReturnEntity qReturn = QReturnEntity.returnEntity;
    protected QInboundEntity qInbound = QInboundEntity.inboundEntity;
    protected QUpperLocation qUpperLocation = QUpperLocation.upperLocation;
    protected QLowerLocation qLowerLocation = QLowerLocation.lowerLocation;

    public List<T> findAll() {
        return this.repository.findAll();
    }

    public BaseService(BaseRepo<T, ID> repository) {
        super(repository);
        this.repository = repository;
    }
}
