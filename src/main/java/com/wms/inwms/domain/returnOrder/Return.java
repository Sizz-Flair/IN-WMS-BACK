package com.wms.inwms.domain.returnOrder;

import com.wms.inwms.domain.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "return_order")
public class Return extends BaseModel<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="order_num")
    private String orderNum;

    @Column(name="agency")
    private String agency;

    @Column(name="delivery_com")
    private String deliveryCom;

    @Column(name="number")
    private String number;

    @Column(name="origin_number")
    private String originNumber;

    @Column(name="qty")
    private Long qty;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="weight")
    @NotNull(message = "{name.notnull}")
    private BigDecimal weight;

    @Column(name="item_num")
    private String itemNum;

    @Column(name="shipper")
    private String shipper;

    @Column(name="agent_id")
    private Long agentId;

    @Transient
    private String error;

    @Override
    public Long getId() {
            return id;
    }
}
