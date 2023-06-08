package com.wms.inwms.domain.returnOrder.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@NoArgsConstructor
public class ReturnOrderSaveDto {
    private Instant dateArrival;
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
}
