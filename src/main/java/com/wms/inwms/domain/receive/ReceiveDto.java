package com.wms.inwms.domain.receive;

import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Getter
public class ReceiveDto {
    private Optional<Instant> startDate;
    private Optional<Instant> endDate;
    private Optional<String> receiveNumber;
    private Optional<List<String>> hwbList;
}
