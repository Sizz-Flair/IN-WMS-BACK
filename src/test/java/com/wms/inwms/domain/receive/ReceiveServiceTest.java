package com.wms.inwms.domain.receive;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ReceiveServiceTest {

    @Autowired
    private ReceiveService receiveService;

    @Test
    @Rollback
    public void testSearchReceivingData() {
        Instant startDate = Instant.parse("2023-01-01T00:00:00Z");
        Instant endDate = Instant.now();
        String reNumber = "test";

        // when
        List<Receive> receiveList = receiveService.searchReceivingData(startDate, endDate, reNumber);

        // then
        assertThat(receiveList).isNotNull();
        assertThat(receiveList).hasSize(1);
        assertThat(receiveList).hasSize(2);
    }
}