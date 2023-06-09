package com.wms.inwms.domain.returnOrder.dto;

import com.wms.inwms.domain.returnOrder.ReturnEntity;
import lombok.*;

import java.math.BigDecimal;

/**
 * packageName    : com.wms.inwms.domain.returnOrder.dto
 * fileName       : ReturnOrderDtoM
 * author         : akfur
 * date           : 2023-06-08
 */
public class ReturnOrderDtoM {

    @Getter
    @AllArgsConstructor
    public static class ReturnSaveDto {
        private String dateArrival;
        private String orderAccount;
        private String agency;
        private String deliveryCom;
        private String number;
        private String originNumber;
        private Integer qty;
        private BigDecimal weight;
        private BigDecimal price;
        private String goodsName;
        private String tel;
        private String addr;
        private String addrDetail;
        private String zipNo;
        private String shipper;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class ReturnExcelDto {
        private String dateArrival;
        private String orderAccount;
        private String agency;
        private String deliveryCom;
        private String number;
        private String originNumber;
        private Integer qty;
        private BigDecimal weight;
        private BigDecimal price;
        private String goodsName;
        private String tel;
        private String addr;
        private String addrDetail;
        private String zipNo;
        private String shipper;
    }
}
