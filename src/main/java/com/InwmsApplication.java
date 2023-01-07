package com;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@Slf4j
//@SpringBootApplication
public class InwmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(InwmsApplication.class, args);
    }

}
