package com.wms.inwms.domain.inbound.dto;

import lombok.*;

import java.time.Instant;

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

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class InboundMappingResultDto {
        private String number;
        private String state;
        private String upperLocation;
        private String lowerLocation;
        private String mappingNum;
        private Instant created;
        private Long amount;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class InboundSelectResultDto{
        private String number;
        private String state;
    }
}
