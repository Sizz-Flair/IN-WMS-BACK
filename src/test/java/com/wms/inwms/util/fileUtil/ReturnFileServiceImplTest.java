package com.wms.inwms.util.fileUtil;

import com.wms.inwms.util.customException.CustomException;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ReturnFileServiceImplTest {

//    @Autowired
//    private ReturnFileServiceImpl returnFileService;

    @Test
    @DisplayName("file 체크")
    public void test() throws IOException, CustomException {
        //given

        ReturnFileServiceImpl returnFileService = new ReturnFileServiceImpl(new FileUtil());
        File file = new File("C:\\Users\\akfur\\Downloads\\반품 입고 리포트_20230405.xlsx");

        //when
        returnFileService.readFile(file);

        //then
        System.out.println("?");
        assertThat();

    }

}