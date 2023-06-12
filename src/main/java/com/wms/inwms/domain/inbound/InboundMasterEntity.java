package com.wms.inwms.domain.inbound;

import com.wms.inwms.domain.base.BaseModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * packageName    : com.wms.inwms.domain.inbound
 * fileName       : InboundMasterEntity
 * author         : akfur
 * date           : 2023-06-12
 */
@Entity
public class InboundMasterEntity extends BaseModel<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public Long getId() {
        return id;
    }
}
