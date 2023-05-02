package com.wms.inwms.domain.returnOrder.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReturnOrderDto {

    @NotNull(message="{NotNull}")
    @NotEmpty
    private String name;

    @NotNull(message="{NotNull}")
    @Min(value=9, message="{min}")
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
