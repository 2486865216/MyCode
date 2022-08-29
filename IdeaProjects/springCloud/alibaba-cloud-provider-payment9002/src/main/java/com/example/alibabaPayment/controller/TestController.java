package com.example.alibabaPayment.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author ye
 * createDate 2022/7/22  19:40
 */
@RestController
public class TestController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/nacos")
    public String testServer(){
        return serverPort;
    }
}
