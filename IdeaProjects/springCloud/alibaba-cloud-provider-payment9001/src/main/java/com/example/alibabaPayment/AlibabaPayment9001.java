package com.example.alibabaPayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * author ye
 * createDate 2022/7/22  19:17
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AlibabaPayment9001 {
    public static void main(String[] args) {
        SpringApplication.run(AlibabaPayment9001.class, args);
    }
}
