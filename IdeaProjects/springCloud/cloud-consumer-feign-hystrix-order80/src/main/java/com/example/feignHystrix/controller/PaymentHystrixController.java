package com.example.feignHystrix.controller;

import com.example.feignHystrix.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * author ye
 * createDate 2022/7/9  12:53
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "global_fallBack_method")
public class PaymentHystrixController {
    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String getOk(@PathVariable("id") Integer id) {
        return paymentHystrixService.getOk(id);
    }

    //@HystrixCommand(fallbackMethod = "getErrorHandle", commandProperties = {
    //        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    //})
    @HystrixCommand
    @GetMapping("/payment/hystrix/error/{id}")
    public String getError(@PathVariable("id") Integer id) {
        int x = 10 / 0;
        return paymentHystrixService.getError(id);
    }

    public String getErrorHandle(@PathVariable("id") Integer id) {
        return "/(ㄒoㄒ)/~~ /(ㄒoㄒ)/~~ /(ㄒoㄒ)/~~ /(ㄒoㄒ)/~~";
    }

    //全局fallback
    public String global_fallBack_method() {
        return "(*^_^*)(*^_^*)(*^_^*)(*^_^*)(*^_^*)(*^_^*)(*^_^*)";
    }
}