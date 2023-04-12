package com.wms.inwms.util.exceptionUtil;

import com.wms.inwms.domain.response.ResponseData;
import com.wms.inwms.domain.response.ResultData;
import com.wms.inwms.util.customException.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final ResponseData responseData;

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<ResultData> handleIllegalArgumentException(NoSuchElementException e) {
        //final ErrorResponse errorResponse = ErrorResponse.builder().code("Item Not Found").message(e.getMessage()).build();

//        return ResponseEntity.status(100).body(() -> "test");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData.ErrorResultData("FAIL"));
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
    protected ResponseEntity<ErrorCode> handleIllegalArgumentException(IllegalArgumentException e) {
        System.out.println("????");
        return null;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ResultData> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        return null;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ResultData> handleConstraintViolationException(ConstraintViolationException e) {
        return null;
    }
}
