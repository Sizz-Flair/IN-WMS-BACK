package com.wms.inwms.domain.returnOrder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//@TestPropertySource("classpath:application.properties")
@ActiveProfiles("application.properties")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReturnRepoTest {

    @Autowired
    ReturnRepository returnRepository;

//
    @Test
    public void test() {
        System.out.println("test");
        System.out.println(returnRepository.findAll());
    }

    @Test
    public void ReturnOrderListSaveTest() {
        //given
        ReturnOrderDto returnOrderDto = ReturnOrderDto.builder()
                .orderNum("testOrderNum")
                .name("testName")
                .deliveryCode("testDelivery")
                .number("testNumber")
                .originNumber("testOrigin")
                .price(new BigDecimal(1L)).build();



        List<ReturnOrderDto> listData = new ArrayList<>();

        listData.add(returnOrderDto);

        ObjectMapper objectMapper = new ObjectMapper();

        List<Return> testData = objectMapper.convertValue(listData, new TypeReference<List<Return>>() { });


        //when

        List<Return>test = returnRepository.saveAll(testData);


        //then
        System.out.println(test);
    }

}