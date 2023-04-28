package com.wms.inwms.domain.returnOrder.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReturnOrderDto {

    @NotNull(message="{NotNull}")
    @NotEmpty
    private String name;

    @NotNull(message="{NotNull}")
    @NotEmpty
    private String telNum;

    @NotNull(message="{NotNull}")
    @NotEmpty
    private String zipNo;

    @NotNull(message="{NotNull}")
    @NotEmpty
    private String addr;

    @NotNull(message="{NotNull}")
    @NotEmpty
    private String goodsName;

    @NotNull(message="{NotNull}")
    private Long qty;

    private String originNumber;
}
