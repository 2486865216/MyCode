package com.example.springbootweb02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//@ServletComponentScan(basePackages = "com.example.springbootweb02")
@SpringBootApplication
public class SpringBootWeb02Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWeb02Application.class, args);
    }

}
