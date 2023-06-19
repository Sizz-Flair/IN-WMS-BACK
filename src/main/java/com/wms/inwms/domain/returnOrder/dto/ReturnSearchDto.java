package com.wms.inwms.domain.returnOrder.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReturnSearchDto {

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;

    private String orderNum;
}
