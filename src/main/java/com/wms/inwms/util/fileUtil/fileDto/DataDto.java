package com.wms.inwms.util.fileUtil.fileDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

public class DataDto {
    public enum ReturnEnum{
        NO("No"),
        NUMBER("반품 송장"),
        ORIGIN_NUMBER("원송장"),
        DELIVERY_CODE("반품 택배 업체"),
        UPPER_LOCATION("장치위치(상위)"),
        LOWER_LOCATION("장치위치(하위)"),
        AGENT_NAME("업체명"),
        RETURN_DATE_TIME("입고 일자"),
        AIR_OCEAN_TYPE("항공/해상"),
        ETC_MEMO("비고");

        private final String value;

        ReturnEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

//    @Getter
//    public static class ReturnDto {
//        public String no = "No";
//        private String number = "반품 송장";
//        private String originNumber = "원송장";
//        private String deliveryCode = "반품 택배 업체";
//        private String upperLocation = "장치위치(상위)";
//        private String lowerLocation = "장치위치(하위)";
//        private String agentName = "업체명";
//        private String returnDateTime = "입고 일자";
//        private String airOceanType = "항공/해상";
//        private String etcMemo = "비고";
//    }
//
//    public ReturnDto getReturnData() {
//        return new ReturnDto();
//    }
}
