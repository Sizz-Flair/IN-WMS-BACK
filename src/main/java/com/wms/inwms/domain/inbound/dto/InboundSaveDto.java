package com.wms.inwms.domain.inbound.dto;

import com.wms.inwms.domain.inbound.InboundEntity;
import com.wms.inwms.domain.location.lowerlocation.LowerLocation;
import com.wms.inwms.domain.location.upperlocation.UpperLocation;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

/**
 * packageName    : com.wms.inwms.domain.inbound.dto
 * fileName       : InboundSaveDto
 * author         : akfur
 * date           : 2023-06-12
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InboundSaveDto {
    private String inboundDate;
    private String number;
    private String state;
    private UpperLocation upperLocation;
    private LowerLocation lowerLocation;
    private String mappingNum;
//    private String createdAt;
//    private String createdBy;

    public InboundEntity convertEntity() {
        return InboundEntity.builder()
                .number(this.number)
                .state(this.state)
                .mappingNum(this.mappingNum)
                .upperLocation(this.upperLocation)
                .lowerLocation(this.lowerLocation).build();
    }
}
