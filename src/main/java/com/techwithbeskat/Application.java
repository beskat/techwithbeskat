package com.techwithbeskat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        log.info("Starting the application()");
        SpringApplication.run(Application.class, args);
    }
}
