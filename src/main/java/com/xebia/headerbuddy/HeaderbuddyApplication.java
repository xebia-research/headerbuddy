package com.xebia.headerbuddy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class HeaderbuddyApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeaderbuddyApplication.class, args);
    }
}
