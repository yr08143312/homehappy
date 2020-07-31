package com.yangrui.homehappy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HomehappyApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomehappyApplication.class, args);
    }

}
