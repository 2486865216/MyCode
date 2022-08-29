package com.example.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * author ye
 * createDate 2022/5/16  9:40
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class PayPartMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PayPartMain8001.class, args);
    }
}
