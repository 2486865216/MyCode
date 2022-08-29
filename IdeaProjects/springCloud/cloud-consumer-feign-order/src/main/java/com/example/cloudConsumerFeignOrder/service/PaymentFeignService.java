package com.example.cloudConsumerFeignOrder.service;

import com.example.springcloud.Moudel.CommonResult;
import com.example.springcloud.Moudel.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * author ye
 * createDate 2022/7/8  13:09
 */
@Service
@FeignClient(value = "CLOUD-PAYMENT-PROVIDER")
public interface PaymentFeignService {
    @GetMapping("/payment/get/{id}")
    CommonResult<Payment> getPayment(@PathVariable("id") Integer id);
}
