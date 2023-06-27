package com.wms.inwms.domain.returnOrder.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

/**
 * packageName    : com.wms.inwms.domain.returnOrder.dto
 * fileName       : ReturnOrderSaveDto
 * author         : akfur
 * date           : 2023-05-04
 */
@Getter
@Setter
public class ReturnOrderSaveDto {
    private Instant dateArrival;

    @NotNull(message="{NotNull}")
    @NotEmpty(message="{NotNull}")
    private String orderNum;
    private String orderAccount;
    private String agency;
    private String deliveryCom;
    private String number;
    private String originNumber;
    private Integer qty;
    private BigDecimal weight;
    private BigDecimal price;
    private String tel;
    private String addr;
    private String addrDetail;
    private String zipNo;
    private String shipper;
    private String goodsName;

    public ReturnOrderSaveDto() {}
    @Builder
    public ReturnOrderSaveDto(Instant dateArrival, String orderAccount, String agency, String deliveryCom,
                              String number, String originNumber, Integer qty,
                              BigDecimal weight, BigDecimal price, String tel,
                              String addr, String addrDetail, String zipNo, String shipper, String goodsName) {
        this.dateArrival = dateArrival;
        this.orderAccount = orderAccount;
        this.agency = agency;
        this.deliveryCom = deliveryCom;
        this.number = number;
        this.originNumber = originNumber;
        this.qty = qty;
        this.weight = weight;
        this.price = price;
        this.tel = tel;
        this.addr = addr;
        this.addrDetail = addrDetail;
        this.zipNo = zipNo;
        this.shipper = shipper;
        this.goodsName = goodsName;
    }

    public ReturnOrderSaveDto convertDto() {
        return ReturnOrderSaveDto.builder()
                .dateArrival(dateArrival)
                .orderAccount(orderAccount)
                .agency(agency)
                .deliveryCom(deliveryCom)
                .number(number)
                .originNumber(originNumber)
                .qty(qty)
                .weight(weight)
                .price(price)
                .tel(tel)
                .addr(addr)
                .addrDetail(addrDetail)
                .zipNo(zipNo)
                .shipper(shipper).goodsName(goodsName).build();
    }
}
