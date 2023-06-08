package com.wms.inwms.domain.returnOrder;

import com.wms.inwms.domain.base.BaseModel;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "return_order")
public class ReturnEntity extends BaseModel<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_num")
    private String orderNum;

    @Column(name = "agency")
    private String agency;

    @Column(name = "delivery_com")
    private String deliveryCom;

    @Column(name = "number")
    private String number;

    @Column(name = "origin_number")
    private String originNumber;

    @Column(name = "qty")
    private Long qty;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "weight")
    private BigDecimal weight;

    @Column(name = "item_num")
    private String itemNum;

    @Column(name = "shipper")
    private String shipper;

    @Column(name = "agent_id")
    private Long agentId;

    @Column(name = "report_status")
    private String reportStatus;

    @Column(name = "goodsName")
    private String goodsName;

    @Column(name = "addr")
    private String addr;

    @Column(name = "addr_detail")
    private String addrDetail;

    @Column(name = "tel")
    private String tel;

    @Column(name = "order_account")
    private String orderAccount;

    @Column(name = "date_arrival")
    private Instant dateArrival;

    @Column(name = "zip_no")
    private String zipNo;


    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    @Override
    public Long getId() {
        return id;
    }
}
