package com.wms.inwms.util.exceptionUtil;

import com.wms.inwms.util.customException.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<ErrorCode> handleIllegalArgumentException(NoSuchElementException e) {
        final ErrorResponse errorResponse = ErrorResponse.builder().code("Item Not Found").message(e.getMessage()).build();

//        return ResponseEntity.status(100).body(() -> "test");
        return null;
    };

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<?> handleCustomException(CustomException e) {
        return null;
    }

    @ExceptionHandler(IOException.class)
    protected ResponseEntity<ErrorCode> handleIOException(IOException e) {
        return null;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ErrorCode> handleIllegalArgumentException(IllegalArgumentException e) { return null; }
}
