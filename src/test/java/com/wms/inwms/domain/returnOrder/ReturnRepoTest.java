package com.wms.inwms.domain.returnOrder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

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
//        ReturnOrderDto returnOrderDto = ReturnOrderDto.builder()
//                .orderNum("testOrderNumaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaatestOrderNumaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
//                .deliveryCode("testDelivery")
//                .number("testNumber")
//                .originNumber("testOrigin")
//                .price(new BigDecimal(1L)).build();
//
//        List<ReturnOrderDto> listData = new ArrayList<>();
//        listData.add(returnOrderDto);
//        ObjectMapper objectMapper = new ObjectMapper();
//        List<Return> testData = objectMapper.convertValue(listData, new TypeReference<List<Return>>() { });

//        ReturnEntity re = ReturnEntity.builder().agentId(1L)
//                .shipper("testShipperaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaatestShipperaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaatestShipperaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaatestShipperaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
//                .agency("testAgency")
//                .number("testNumber")
//                .deliveryCom("testDecom")
//                .originNumber("testOrigin")
//                .itemNum("testItemNum")
//                .qty(1L)
//                .orderNum("testOrderNum")
//                .price(new BigDecimal(1L))
//                .weight(new BigDecimal(1L)).build();
//
//        List<ReturnEntity> returnListData = new ArrayList<>();
//        returnListData.add(re);

        //when
        //List<ReturnEntity>test = returnRepository.saveAll(returnListData);

        //then
        //System.out.println(test);
    }
}