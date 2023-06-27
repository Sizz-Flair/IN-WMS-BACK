package com.wms.inwms.domain.mapper.cj;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * packageName    : com.wms.inwms.domain.mapper.cj
 * fileName       : CJTrackingDto
 * author         : akfur
 * date           : 2023-06-26
 */
@Getter
@Setter
public class CJTrackingDto {
    private String number;
    private String orderAccount;
    private Instant created;
}
