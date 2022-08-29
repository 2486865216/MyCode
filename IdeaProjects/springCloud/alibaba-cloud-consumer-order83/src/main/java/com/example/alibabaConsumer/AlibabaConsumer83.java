package com.example.alibabaConsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * author ye
 * createDate 2022/7/22  19:52
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AlibabaConsumer83 {
    public static void main(String[] args) {
        SpringApplication.run(AlibabaConsumer83.class, args);
    }
}
