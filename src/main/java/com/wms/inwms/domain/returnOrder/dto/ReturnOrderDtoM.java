package com.wms.inwms.domain.returnOrder.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.wms.inwms.domain.returnOrder.ReturnEntity;
import lombok.*;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

/**
 * packageName    : com.wms.inwms.domain.returnOrder.dto
 * fileName       : ReturnOrderDtoM
 * author         : akfur
 * date           : 2023-06-08
 */
public class ReturnOrderDtoM {

    @Getter
    @Setter
    @Builder
    public static class ReturnOrderGroupDto {
        private String orderNumber;
        private String agency;
        private Integer qty;

        @QueryProjection
        public ReturnOrderGroupDto(String orderNumber, String agency, Integer qty) {
            this.orderNumber = orderNumber;
            this.agency = agency;
            this.qty = qty;
        }
    }

    @Getter
    @Setter
    @Builder
    public static class ReturnOrderPickingDto {
        @QueryProjection
        public ReturnOrderPickingDto(String upperLocation, String lowerLocation, String number, String goodsName) {
            this.upperLocation = upperLocation;
            this.lowerLocation = lowerLocation;
            this.number = number;
            this.goodsName = goodsName;
        }

        private String upperLocation;
        private String lowerLocation;
        private String number;
        private String goodsName;
    }


    @Getter
    @Setter
    public static class ReturnOrderSearchDto {
        private String number;
        private String orderAccount;
        private String deliveryCom;
        private Instant created;
        private Integer pageable;
    }

    @Getter
    @Setter
    @Builder
    public static class ReturnSaveDto {
        public ReturnSaveDto(String orderNum, String reportStatus, String dateArrival, String orderAccount, String agency, String deliveryCom, String number, String originNumber, Integer qty, BigDecimal weight, BigDecimal price, String goodsName, String tel, String addr, String addrDetail, String zipNo, String shipper) {
            this.orderNum = orderNum;
            this.dateArrival = dateArrival;
            this.orderAccount = orderAccount;
            this.agency = agency;
            this.deliveryCom = deliveryCom;
            this.number = number;
            this.originNumber = originNumber;
            this.qty = qty;
            this.weight = weight;
            this.price = price;
            this.goodsName = goodsName;
            this.tel = tel;
            this.addr = addr;
            this.addrDetail = addrDetail;
            this.zipNo = zipNo;
            this.shipper = shipper;
            this.reportStatus = reportStatus;
        }

        private String orderNum;
        private String reportStatus;
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
