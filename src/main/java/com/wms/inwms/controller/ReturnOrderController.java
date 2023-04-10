package com.wms.inwms.controller;

import com.wms.inwms.util.customException.CustomException;
import com.wms.inwms.util.fileUtil.ReturnFileServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ReturnOrderController {

    private final ReturnFileServiceImpl returnFileService;
    //@RequestParam MultipartFile file
    @PostMapping(path="/return")
    public String test(@RequestParam MultipartFile file) throws IOException, CustomException {


        returnFileService.readFile(new File("D:\\data\\excelfile\\반품 입고 리포트_20230405"));

        return "";
    }
}
