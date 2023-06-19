package com.wms.inwms.domain.returnOrder.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.wms.inwms.domain.returnOrder.dto
 * fileName       : ReturnResponseDto
 * author         : akfur
 * date           : 2023-05-03
 *
 * -- Entity 반환 또는 Dto반환 고민 중 그냥  Dto를 세분화하기로 함
 */
@Getter
@Setter
public class ReturnResponseDto {
    private String agency;
    private String deliveryCom;
    private String number;

}
