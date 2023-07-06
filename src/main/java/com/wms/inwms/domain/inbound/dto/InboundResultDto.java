package com.wms.inwms.domain.inbound.dto;

import lombok.*;

import java.time.Instant;

public class InboundResultDto {

    @Builder
    @NoArgsConstructor
    @Getter
    @Setter
    public static class InboundSaveResultDto {
        public InboundSaveResultDto(String number, String state, String upperLocation, String lowerLocation, String mappingNum, String agentCode) {
            this.number = number;
            this.state = state;
            this.upperLocation = upperLocation;
            this.lowerLocation = lowerLocation;
            this.mappingNum = mappingNum;
            this.agentCode = agentCode;
        }

        private String number;
        private String state;
        private String upperLocation;
        private String lowerLocation;
        private String mappingNum;
        private String agentCode;
    }

    @Builder
    @NoArgsConstructor
    @Getter
    @Setter
    public static class InboundMappingResultDto {
        public InboundMappingResultDto(String number, String state, String upperLocation, String lowerLocation, String mappingNum, Instant created, Long amount, String agentCode) {
            this.number = number;
            this.state = state;
            this.upperLocation = upperLocation;
            this.lowerLocation = lowerLocation;
            this.mappingNum = mappingNum;
            this.created = created;
            this.amount = amount;
            this.agentCode = agentCode;
        }

        private String number;
        private String state;
        private String upperLocation;
        private String lowerLocation;
        private String mappingNum;
        private Instant created;
        private Long amount;
        private String agentCode;
    }

    @Builder
    @NoArgsConstructor
    @Getter
    @Setter
    public static class InboundSelectResultDto{
        public InboundSelectResultDto(String number, String state) {
            this.number = number;
            this.state = state;
        }

        private String number;
        private String state;
    }
}
