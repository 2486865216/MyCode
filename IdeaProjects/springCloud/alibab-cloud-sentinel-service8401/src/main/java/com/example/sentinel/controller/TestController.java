package com.example.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * author ye
 * createDate 2022/7/25  20:27
 */
@RestController
@Slf4j
public class TestController {

    @GetMapping("/testA")
    public String test(){
        return "testA    " + UUID.randomUUID().toString();
    }

    @GetMapping("/testB")
    public String test1(){
        System.out.println(Thread.currentThread().getName());
        return "testB    "  + UUID.randomUUID().toString();
    }

    @GetMapping("/testD")
    public String test2(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("testD 测试RT");
        return "testD    "  + UUID.randomUUID().toString();
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2){
        return "test Hot key .................";
    }

    public String deal_testHotKey(String pa, String p2, BlockException exception){
        return "deal_testHotKey/(ㄒoㄒ)/~~";
    }
}
