package com.example.springcloud.controller;

import com.example.springcloud.Moudel.CommonResult;
import com.example.springcloud.Moudel.Payment;
import com.example.springcloud.service.serviceImpl.PaymentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author ye
 * createDate 2022/5/16  10:09
 */
@RestController
@Slf4j
public class PatmentController8001 {

    @Autowired
    private PaymentServiceImpl paymentService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult<Integer> create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        if (result > 0){
            return new CommonResult<>(200, "插入数据库成功,serverPort: " + serverPort , result);
        }else {
            return new CommonResult<>(444, "插入数据库失败", null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Integer id){
        Payment result = paymentService.getPayment(id);
        if (result != null){
            return new CommonResult<>(200, "查询成功,serverPort: " + serverPort, result);
        }else {
            return new CommonResult<>(444, "查询失败", null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            System.out.println("element = " + service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-PROVIDER");
        for (ServiceInstance instance : instances) {
            log.info(instance.getInstanceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }

        return this.discoveryClient;
    }

    @GetMapping("/payment/lb")
    public String getServerPort() {
        return serverPort;
    }

    @GetMapping("/payment/zipkin")
    public String testZipkin(){
        return "Hi zipkin";
    }
}
