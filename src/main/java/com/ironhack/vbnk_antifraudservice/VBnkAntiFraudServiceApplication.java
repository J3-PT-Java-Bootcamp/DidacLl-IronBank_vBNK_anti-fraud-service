package com.ironhack.vbnk_antifraudservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class VBnkAntiFraudServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VBnkAntiFraudServiceApplication.class, args);
    }

}
