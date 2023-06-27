package com.wms.inwms.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * packageName    : com.wms.inwms.config
 * fileName       : ViewInterceptor
 * author         : akfur
 * date           : 2023-06-21
 */
//public class ViewInterceptor implements HandlerInterceptor {
//
////    @Override
////    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
////        String url = String.valueOf(request.getRequestURI());
////        if(url.contains("/view")) {
////            response.setStatus(HttpServletResponse.SC_OK);
////            response.setContentType("text/html;charset=UTF-8");
////            response.getWriter().write("/static/index.html");
////            return false;
////        }
////        return true;
////    }
//}
