package com.example.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * author ye
 * createDate 2022/7/28  19:57
 */
@RestController
@Slf4j
public class OrderController {
    public static final String SERVER_UTL = "http://provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback")//没配置
    @SentinelResource(value="fallback", fallback="handlerFallback")//fallback只负责业务异常
    //@SentinelResource(value="fallback",blockHandler="bLockHandler")//bLockHandler只负责sentinel控制台配置违规
    //@SentinelResource(value "fallback",fallback="handlerFallback",blockHandler="blockHandler")
    public String fallback(@PathVariable("id") Long id) {
        if (id == 4) {
            throw new IllegalArgumentException("非法参数异常");
        }
        return restTemplate.getForObject(SERVER_UTL + "/payment/" + id, String.class);
    }

    public String handlerFallback(@PathVariable("id") Long id, Throwable e) {
        return "handlerFallback";
    }
    public String bLockHandler(BlockException exception) {
        return "bLockHandler";
    }

    //=============openFeign================
    //@Resource
    //private PaymentService paymentService;
    //
    //@GetMapping("/consumer/payment/{id}")
    //public String payment(@PathVariable("id") Long id){
    //    return paymentService.payment(id);
    //}
}
