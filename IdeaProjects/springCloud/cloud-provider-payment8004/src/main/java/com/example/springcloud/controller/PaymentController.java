package com.example.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * author ye
 * createDate 2022/5/25  9:14
 */
@RestController
@Slf4j
public class PaymentController {
    @Value("server.port")
    private String serverPort;

    @RequestMapping("/payment/zk")
    public String paymentzk() {
        return "springCloud with zookeeper:" + serverPort + "\t" + UUID.randomUUID();
    }
}
