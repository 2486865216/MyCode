package com.example.rabbitmq.springbootrabbitmq.controller;

import com.example.rabbitmq.springbootrabbitmq.config.DelayedQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * author ye
 * createDate 2022/3/3  21:49
 * 发送延迟消息
 */
@Slf4j
@RestController
@RequestMapping("/ttl")
public class sendMessageController {
    //开始发消息
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @GetMapping("/sendMessage/{message}")
    public void sendMessage(@PathVariable("message") String message) {
        log.info("当前时间:{}, 发送一条信息给了两个队列:{}",new Date(), message);
        rabbitTemplate.convertAndSend("X", "XA", "消息来自ttl为10s的队列");
        rabbitTemplate.convertAndSend("X", "XB", "消息来自ttl为40s的队列");
    }

    //发消息和TTL
    @GetMapping("/sendExpirationMessage/{message}/{ttltime}")
    public void sendExpirationMessage(@PathVariable("message") String message, @PathVariable("ttltime") String ttltime) {
        log.info("当前时间:{}, 发送一条时长为:{}ms,信息给了一个队列:{}",new Date(), ttltime, message);
        rabbitTemplate.convertAndSend("X", "XC", message ,msg -> {
            //设置TTL
            msg.getMessageProperties().setExpiration(ttltime);
            return msg;
        });
    }

    //插件发消息
    @GetMapping("/sendDelayedMessage/{message}/{delayedTime}")
    public void sendDelayedMessage(@PathVariable("message") String message, @PathVariable("delayedTime") Integer delayedTime) {
        log.info("当前时间:{}, 发送一条时长为:{}ms,信息给了一个delayed队列:{}",new Date(), delayedTime, message);
        rabbitTemplate.convertAndSend(DelayedQueueConfig.DELAYED_EXCHANGE, DelayedQueueConfig.DELAYED_ROUTINGKEY, message , msg -> {
            //设置TTL
            msg.getMessageProperties().setDelay(delayedTime);
            return msg;
        });
    }
}
