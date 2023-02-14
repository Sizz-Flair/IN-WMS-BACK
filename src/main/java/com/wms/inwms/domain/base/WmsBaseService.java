package com.wms.inwms.domain.base;

import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.wms.inwms.domain.base.BaseRepo;
import com.wms.inwms.domain.base.WmsFilterService;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

public abstract class WmsBaseService<T, ID extends Serializable> extends WmsFilterService<T> {
    protected BaseRepo<T, ID> repository;

    @PersistenceContext
    protected EntityManager em;

    public WmsBaseService() {}

    public WmsBaseService(BaseRepo<T, ID> repository) {
        this.repository = repository;
    }

    @Transactional
    public <S extends T> S save(S object) {
        return (S)this.repository.save(object);
    }

    protected JPAQuery<T> select() {
        return new JPAQuery(this.em);
    }

    protected JPAUpdateClause update(EntityPath<?> entityPath) {
        return new JPAUpdateClause(this.em, entityPath);
    }

    protected JPADeleteClause delete(EntityPath<?> entityPath) {
        return new JPADeleteClause(this.em, entityPath);
    }
}
