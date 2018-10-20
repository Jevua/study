package com.example.myspringmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com")
public class MyspringmvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyspringmvcApplication.class, args);
    }
}
