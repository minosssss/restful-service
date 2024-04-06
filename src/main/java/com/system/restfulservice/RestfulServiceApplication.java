package com.system.restfulservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RestfulServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(RestfulServiceApplication.class, args);


    }

}
