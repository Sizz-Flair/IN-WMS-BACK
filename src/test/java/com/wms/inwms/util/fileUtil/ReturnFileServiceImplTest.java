package com.wms.inwms.util.fileUtil;

import com.wms.inwms.util.customException.CustomException;


import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ReturnFileServiceImplTest {

//    @Autowired
//    private ReturnFileServiceImpl returnFileService;

    @Test
    @DisplayName("file 체크")
    public void test() throws IOException, CustomException {
        //given

//        ReturnFileServiceImpl returnFileService = new ReturnFileServiceImpl(new FileUtil());
//        //File file = new File("C:\\Users\\akfur\\Downloads\\반품 입고 리포트_20230405.xlsx");
//        File file = new File("D:\\data\\excelfile\\반품 입고 리포트_20230405.xlsx");
//        FileItem fileItem = new DiskFileItem("file", Files.probeContentType(file.toPath()), false, file.getName(), (int)file.length(), file.getParentFile());
//
//        InputStream input = new FileInputStream(file);
//        OutputStream os = fileItem.getOutputStream();
//        IOUtils.copy(input, os);
//
//        MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
//
//        //when
//        List<Map<String, String>> exData = returnFileService.readFile(file);

        //then
        //assertNotNull(exData);


    }

}