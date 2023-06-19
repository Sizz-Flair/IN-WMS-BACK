package com.wms.inwms.util.exceptionUtil;

import lombok.Builder;

@Builder
public class ErrorResponse {
    private String code;
    private String message;
}
