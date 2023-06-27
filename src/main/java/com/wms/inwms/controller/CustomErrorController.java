//package com.wms.inwms.controller;
//
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import javax.servlet.http.HttpServletResponse;
//
///**
// * packageName    : com.wms.inwms.controller
// * fileName       : CustomErrorController
// * author         : akfur
// * date           : 2023-06-20
// */
//@Controller
//public class CustomErrorController implements ErrorController {
//
//    @GetMapping(path = "/view/**")
//    public String view(HttpServletResponse response) {
//        response.setStatus(HttpServletResponse.SC_OK);
//        return "/static/index.html";
//    }
//}
