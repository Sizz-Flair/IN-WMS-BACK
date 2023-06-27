package com.wms.inwms.aop.logging;

import com.wms.inwms.domain.response.ResponseData;
import com.wms.inwms.domain.response.ResponseMessage;
import com.wms.inwms.domain.response.ResultPageData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * packageName    : com.wms.inwms.aop.logging
 * fileName       : LogAspect
 * author         : akfur
 * date           : 2023-06-23
 */
@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class LogAspect {

    private final ResponseData responseData;

    @Around("execution(org.springframework.http.ResponseEntity<com.wms.inwms.domain.response.ResultPageData> com.wms.inwms.controller.*.*(..))")
    public ResponseEntity<ResultPageData> advice(@NotNull ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        log.info("tsxt!!!!!!!!!");
        result = joinPoint.proceed();
        log.info("?");

        return (ResponseEntity<ResultPageData>) result;
    }
}
