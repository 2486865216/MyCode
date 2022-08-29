package com.example.cloudConsumerFeignOrder.controller;

import com.example.cloudConsumerFeignOrder.service.PaymentFeignService;
import com.example.springcloud.Moudel.CommonResult;
import com.example.springcloud.Moudel.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * author ye
 * createDate 2022/7/8  13:14
 */
@RestController
@Slf4j
public class OrderFeignController {
    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Integer id){
        return paymentFeignService.getPayment(id);
    }
}
