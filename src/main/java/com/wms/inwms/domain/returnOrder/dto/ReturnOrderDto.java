package com.wms.inwms.domain.returnOrder.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReturnOrderDto {

    @NotNull(message="{NotNull}")
    @NotEmpty
    private String shipper;

    @NotNull(message="{NotNull}")
   //@Min(value=9, message="{min}")
    @NotEmpty
    private String tel;

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