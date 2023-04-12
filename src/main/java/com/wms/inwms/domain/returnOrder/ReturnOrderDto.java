package com.wms.inwms.domain.returnOrder;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ReturnOrderDto {

    @NotNull
    private String orderNum;

    @NotEmpty
    private String name;
}
