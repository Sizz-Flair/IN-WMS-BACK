package com.wms.inwms.domain.returnOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ReturnOrderDto {

    @NotNull
    @NotEmpty
    private String orderNum;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String deliveryCode;

    @NotNull
    @NotEmpty
    private String number;

    @NotNull
    @NotEmpty
    private String originNumber;

    private BigDecimal price;

}
