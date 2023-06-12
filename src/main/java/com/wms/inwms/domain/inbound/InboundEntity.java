package com.wms.inwms.domain.inbound;

import com.wms.inwms.domain.base.BaseModel;

import javax.persistence.*;

/**
 * packageName    : com.wms.inwms.domain.inbound
 * fileName       : Inbound
 * author         : akfur
 * date           : 2023-06-12
 */
@Entity
public class InboundEntity extends BaseModel<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="inbound_number")
    private String inboundNumber;

    @Override
    public Long getId() {
        return null;
    }
}
