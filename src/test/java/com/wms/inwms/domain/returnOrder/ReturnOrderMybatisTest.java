package com.wms.inwms.domain.returnOrder;

import com.wms.inwms.domain.mapper.cj.CJDeliveryDto;
import com.wms.inwms.domain.mapper.cj.CjMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * packageName    : com.wms.inwms.domain.returnOrder
 * fileName       : ReturnOrderMybatisTest
 * author         : akfur
 * date           : 2023-05-02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReturnOrderMybatisTest {

    @Autowired
    private CjMapper cjMapper;

    @Test
    @DisplayName("CJ 반송 테스트")
    public void mybatisTest() {

        //given

        //when
        //cjMapper.send(cjDeliveryDto);

        //then

    }
}
