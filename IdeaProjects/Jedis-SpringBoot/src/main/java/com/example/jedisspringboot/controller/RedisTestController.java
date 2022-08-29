package com.example.jedisspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redisTest")
public class RedisTestController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping
    public String testRedis(){
        //设置值到redis
        redisTemplate.opsForValue().set("name", "lucy");
        //获取
        Object o = redisTemplate.opsForValue().get("name");
        return o.toString();
    }

    @GetMapping("testLock")
    public void testLock(){
        String uuid = UUID.randomUUID().toString();
        //1 获取锁
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", uuid, 3 , TimeUnit.SECONDS);
        //2/获取锁成功，查询num的值
        if (lock){
            Object value = redisTemplate.opsForValue().get("num");
            if ((StringUtils.isEmpty(value))){
                return;
            }
            int num = Integer.parseInt(value+"");

            redisTemplate.opsForValue().set("num", ++num);

            if (uuid.equals(redisTemplate.opsForValue().get("lock").toString())){
                redisTemplate.delete("lock");
            }
        }else {
            //获取锁失败，每隔0.1秒重试
            try {
                Thread.sleep(100);
                testLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
