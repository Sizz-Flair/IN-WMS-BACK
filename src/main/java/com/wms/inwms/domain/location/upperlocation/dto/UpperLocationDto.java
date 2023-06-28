package com.wms.inwms.domain.location.upperlocation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.wms.inwms.domain.location.upperlocation.dto
 * fileName       : UpperLocationDto
 * author         : akfur
 * date           : 2023-06-27
 */
public class UpperLocationDto {

    @Getter
    @Setter
    @Builder
    public static class UpperLocationResultDto {
        public UpperLocationResultDto(String locationName, String locationType, Integer lowLcCount, Integer inboundDataCount) {
            this.locationName = locationName;
            this.locationType = locationType;
            this.lowLcCount = lowLcCount;
            this.inboundDataCount = inboundDataCount;
        }

        private String locationName;
        private String locationType;
        private Integer lowLcCount;
        private Integer inboundDataCount;
    }
}
