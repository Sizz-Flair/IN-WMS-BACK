package com.wms.inwms.config;

import com.wms.inwms.util.SecurityUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * packageName    : com.wms.inwms.config
 * fileName       : ViewInterceptor
 * author         : akfur
 * date           : 2023-06-21
 */
@Slf4j
public class ViewInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logSet(request);
        return true;
    }

    private void logSet(HttpServletRequest request) {
        String user = SecurityUserUtil.getUserDetailInfo().getUsername();
        log.info(user + " - " + request.getRequestURI());
    }
}
