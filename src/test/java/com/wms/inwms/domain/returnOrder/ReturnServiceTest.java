package com.wms.inwms.domain.returnOrder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wms.inwms.domain.returnOrder.dto.ReturnOrderDto;
import net.sf.jasperreports.engine.util.JRStyledText;
import org.apache.poi.ss.formula.functions.T;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//@TestPropertySource("classpath:application.properties")
//@ActiveProfiles("application.properties")
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
public class ReturnServiceTest {

    //static int a;

    @Mock
    ReturnRepository returnRepository;
    @Mock
    ObjectMapper objectMapper;

    @InjectMocks
    ReturnService returnService;
//
    @Test
    public void test(int i) {
        System.out.println("test");
        System.out.println(returnService.findAll());
    }

    class Myr implements  Runnable {
        private String message;

        public Myr(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            while (true) {
                System.out.println(this.message);
            }
        }
    }

    @Test
    public void t() throws InterruptedException {


//        Runnable runnable = () -> {
//            System.out.println(Thread.currentThread().getName());

//            for(int i=0; i<1000000; i++) {
//                a =2;
//            }
//        };
//
//        Runnable runnable1 = () -> {
//            System.out.println(Thread.currentThread().getName());
//            for(int i=0; i<1000000; i++) {
//                a -=1;
//            }
//        };

        //Myr myr = new Myr("test");

//        Runnable runnable = () -> {
//            while(true) {
//                System.out.println("test");
//            }
//        };
//
//        Runnable runnable1 = () -> {
//            while (true) {
//                System.out.println("=========?");
//            }
//        };
//
//        Thread thread = new Thread(runnable);
//        thread.start();;
//
//        Thread thread1 = new Thread(runnable1);
//        thread1.start();

//        Thread thread = new Thread(runnable);
//        Thread thread1 = new Thread(runnable1);
//
//        thread.start();
//        thread1.start();
//
//        thread.join();
//        thread1.join();
//        System.out.println(a);

    }

    private static Map<Integer, Integer> hashMap = new HashMap<>();
    @Test
    @DisplayName("builder")
    public void test2() throws InterruptedException {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            final int key = i;
            executorService.submit(() -> {
                for (int j = 0; j < 1000; j++) {
                    synchronized (hashMap) {
                        hashMap.put(key, j);
                    }
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("Result: " + hashMap.size());


    }

    @Test
    @DisplayName("반품오더")
    public void returnOrderSaveTest() {
        //given
//        ReturnOrderDto returnOrderDto = ReturnOrderDto.builder()
//                .orderNum("testOrderNum")
//                .name("testName")
//                .deliveryCode("testDelivery")
//                .number("testNumber")
//                .originNumber("testOrigin")
//                .price(new BigDecimal(1L)).build();


        //when

        //then


    }
}