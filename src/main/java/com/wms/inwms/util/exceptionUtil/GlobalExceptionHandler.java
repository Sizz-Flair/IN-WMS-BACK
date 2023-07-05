package com.wms.inwms.util.exceptionUtil;

import com.wms.inwms.domain.response.ResponseData;
import com.wms.inwms.domain.response.ResultData;
import com.wms.inwms.util.customException.CustomException;
import com.wms.inwms.util.customException.CustomRunException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
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
@Slf4j
public class GlobalExceptionHandler {
    private final ResponseData responseData;

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<ResultData> handleNoSuchElementException(NoSuchElementException e) {
        log.error("NoSuchElementException : " + e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData.ErrorResultData(e.getMessage()));
    };

    @ExceptionHandler(IOException.class)
    protected ResponseEntity<ResultData> handleIOException(IOException e) {
        log.error("handleIOException : " + e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData.ErrorResultData(e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ResultData> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("IllegalArgumentException : " + e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData.ErrorResultData(e.getMessage()));
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
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData.ErrorResultData(e.getMessage()));
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    protected ResponseEntity<ResultData> handleIndexOutOfBoundsException(IndexOutOfBoundsException e) {
        log.error("IndexOutOfBoundsException", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData.ErrorResultData(e.getMessage()));
    }

    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<ResultData> handleNullPointException(NullPointerException e) {
        log.error("NullPointerException",e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData.ErrorResultData(e.getMessage()));
    }

    @ExceptionHandler(DuplicateKeyException.class)
    protected ResponseEntity<ResultData> handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("DuplicateKeyException",e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData.ErrorResultData(e.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<ResultData> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error("DataIntegrityViolationException",e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData.ErrorResultData(e.getMessage()));
    }
}
