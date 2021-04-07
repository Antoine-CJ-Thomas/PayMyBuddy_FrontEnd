package com.paymybuddy.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.paymybuddy.app")
public class App {

    private static final Logger logger = LogManager.getLogger("App");
    
    public static void main(String[] args) {
        logger.info("main");

		SpringApplication.run(App.class, args);
    }
}
