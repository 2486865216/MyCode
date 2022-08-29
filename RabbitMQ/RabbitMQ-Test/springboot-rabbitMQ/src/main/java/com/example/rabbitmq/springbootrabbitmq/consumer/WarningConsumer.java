package com.example.rabbitmq.springbootrabbitmq.consumer;

import com.example.rabbitmq.springbootrabbitmq.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * author ye
 * createDate 2022/3/13  19:36
 * 警告队列
 */
@Slf4j
@Component
public class WarningConsumer {
    @RabbitListener(queues = ConfirmConfig.WARNING_QUEUE_NAME)
    public void receiveWarningMessage(Message message){
        log.info("接收到的不可路由的消息：{}", new String(message.getBody()));
    }
}
