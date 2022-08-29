package com.example.feignHystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

/**
 * author ye
 * createDate 2022/7/9  13:32
 */
@Service
public class PaymentHystrixServiceImpl implements PaymentHystrixService{
    @Override
    public String getOk(Integer id) {
        return "(*^_^*)";
    }

    @Override
    public String getError(Integer id) {
        return "/(ㄒoㄒ)/~~";
    }
}
