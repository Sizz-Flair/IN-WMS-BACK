package com.wms.inwms.domain.inbound.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class InboundSaveDto {
    private String number;
    private String state;
    private String upperLocation;
    private String lowerLocation;
    private String createdAt;
    private String createdBy;
}
