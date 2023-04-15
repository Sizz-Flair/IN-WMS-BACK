package com.wms.inwms.domain.returnOrder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wms.inwms.domain.receive.ReceiveRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//@TestPropertySource("classpath:application.properties")
//@ActiveProfiles("application.properties")
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
public class ReturnServiceTest {

    @Mock
    ReturnRepository returnRepository;
    @Mock
    ObjectMapper objectMapper;

    @InjectMocks
    ReturnService returnService;
//
    @Test
    public void test() {
        System.out.println("test");
        System.out.println(returnService.findAll());
    }

    @Test
    @DisplayName("반품오더")
    public void returnOrderSaveTest() {
        //given
        ReturnOrderDto returnOrderDto = ReturnOrderDto.builder()
                .orderNum("testOrderNum")
                .name("testName")
                .deliveryCode("testDelivery")
                .number("testNumber")
                .originNumber("testOrigin")
                .price(new BigDecimal(1L)).build();


        //when

        //then


    }
}