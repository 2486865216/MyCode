package com.example.payment.controller;

import com.example.springcloud.Moudel.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.UUID;

/**
 * author ye
 * createDate 2022/7/28  19:41
 */
@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, String> hashMap = new HashMap<>();
    static {
        hashMap.put(1L, UUID.randomUUID().toString());
        hashMap.put(2L, UUID.randomUUID().toString());
        hashMap.put(3L, UUID.randomUUID().toString());
    }

    @GetMapping("/payment/{id}")
    public String payment(@PathVariable("id") Long id) {
        String str = hashMap.get(id);
        return str + "===========" + serverPort;
    }
}
