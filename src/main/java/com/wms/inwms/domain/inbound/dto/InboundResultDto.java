package com.wms.inwms.domain.inbound.dto;

import lombok.*;

public class InboundResultDto {

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class InboundSaveResultDto {
        private String number;
        private String state;
        private String upperLocation;
        private String lowerLocation;
        private String mappingNum;
    }
}
