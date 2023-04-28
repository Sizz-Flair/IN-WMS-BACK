package com.wms.inwms.util.exceptionUtil;

import com.wms.inwms.domain.response.ResponseData;
import com.wms.inwms.domain.response.ResultData;
import com.wms.inwms.util.customException.CustomException;
import com.wms.inwms.util.customException.CustomRunException;
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
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData.ErrorResultData(e.getMessage()));
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

    @ExceptionHandler(CustomRunException.class)
    protected ResponseEntity<ResultData> handleCustomRunException(CustomRunException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData.ErrorResultData(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ResultData> handleException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData.ErrorResultData(e.getMessage()));
    }
}
