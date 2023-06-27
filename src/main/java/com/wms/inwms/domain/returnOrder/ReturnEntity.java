package com.wms.inwms.domain.returnOrder;

import com.wms.inwms.domain.base.BaseModel;
import com.wms.inwms.domain.returnOrder.dto.ReturnOrderDtoM;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "return_order")
public class ReturnEntity extends BaseModel<Long> {
    public ReturnEntity() {}

    public ReturnEntity(Long id, String orderNum, String agency, String deliveryCom, String number, String originNumber, Integer qty, BigDecimal price, BigDecimal weight, String itemNum, String shipper, Long agentId, String reportStatus, String goodsName, String addr, String addrDetail, String tel, String orderAccount, Instant dateArrival, String zipNo) {
        this.id = id;
        this.orderNum = orderNum;
        this.agency = agency;
        this.deliveryCom = deliveryCom;
        this.number = number;
        this.originNumber = originNumber;
        this.qty = qty;
        this.price = price;
        this.weight = weight;
        this.itemNum = itemNum;
        this.shipper = shipper;
        this.agentId = agentId;
        this.reportStatus = reportStatus;
        this.goodsName = goodsName;
        this.addr = addr;
        this.addrDetail = addrDetail;
        this.tel = tel;
        this.orderAccount = orderAccount;
        this.dateArrival = dateArrival;
        this.zipNo = zipNo;
    }

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
    private Integer qty;

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

    public ReturnOrderDtoM.ReturnSaveDto convertDto() {
        return ReturnOrderDtoM.ReturnSaveDto.builder()
                .orderNum(orderNum)
                .dateArrival(dateArrival.toString())
                .orderAccount(orderAccount)
                .agency(agency)
                .deliveryCom(deliveryCom)
                .number(number)
                .originNumber(originNumber)
                .qty(qty)
                .weight(weight)
                .price(price)
                .goodsName(goodsName)
                .tel(tel)
                .addr(addr)
                .addrDetail(addrDetail)
                .zipNo(zipNo)
                .shipper(shipper).reportStatus(reportStatus).build();
    }

    @Override
    public Long getId() {
        return id;
    }
}
