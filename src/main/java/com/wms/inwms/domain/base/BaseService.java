package com.wms.inwms.domain.base;

import java.io.Serializable;

public class BaseService<T, ID extends Serializable> extends WmsBaseService<T, ID> {

    protected BaseRepo<T, ID> repository;

    public BaseService() {}

    public BaseService(BaseRepo<T, ID> repository) {
        super(repository);
        this.repository = repository;
    }
}
