package com.example.cloudConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * author ye
 * createDate 2022/7/18  19:13
 */
@SpringBootApplication
@EnableEurekaClient
public class ConfigMain3366 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigMain3366.class, args);
    }
}
