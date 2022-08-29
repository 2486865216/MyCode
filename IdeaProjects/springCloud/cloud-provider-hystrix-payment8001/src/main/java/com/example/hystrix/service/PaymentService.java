package com.example.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * author ye
 * createDate 2022/7/9  12:27
 */
@Service
public class PaymentService {
    /**
     * 正常访问
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id) {
        return  "线程池" + Thread.currentThread().getName() + " payment_OK id : " + id;
    }

    /**
     * 模拟异常
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_ERRORHandle", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_ERROR(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  "线程池" + Thread.currentThread().getName() + " payment_ERROR id : " + id;
    }

    public String paymentInfo_ERRORHandle(Integer id) {
        return  "线程池" + Thread.currentThread().getName() + " payment_ERROR id : " + id + "  /(ㄒoㄒ)/~~";
    }

    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerHandle", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),//失败率达到多少跳闸
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("id不能为负数");
        }
        String simpleUUID = UUID.randomUUID().toString().substring(0 , 6);
        return Thread.currentThread().getName() + "    " + simpleUUID;
    }
    public String paymentCircuitBreakerHandle(Integer id) {
        return "处理方案";
    }
}
