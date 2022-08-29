package com.example.hystrix.controller;

import com.example.hystrix.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * author ye
 * createDate 2022/7/9  12:30
 */
@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String getOk(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_OK(id);
        log.info("result = " + result);
        return result;
    }

    @GetMapping("/payment/hystrix/error/{id}")
    public String getError(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_ERROR(id);
        log.info("result = " + result);
        return result;
    }

    //服务熔断
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("result = " + result);
        return result;
    }
}