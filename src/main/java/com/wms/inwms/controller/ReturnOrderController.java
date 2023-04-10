package com.wms.inwms.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ReturnOrderController {

    //@RequestParam MultipartFile file
    @PostMapping(path="/return")
    public String test(@RequestParam MultipartFile file) {



        return "";
    }
}
