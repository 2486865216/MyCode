package com.example.feignHystrix.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * author ye
 * createDate 2022/7/9  12:51
 */
@Service
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT", fallback = PaymentHystrixServiceImpl.class)
public interface PaymentHystrixService {
    @GetMapping("/payment/hystrix/ok/{id}")
    String getOk(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/error/{id}")
    String getError(@PathVariable("id") Integer id);
}
