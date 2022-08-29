package com.example.rabbitmq.springbootrabbitmq.consumer;

import com.example.rabbitmq.springbootrabbitmq.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * author ye
 * createDate 2022/3/5  21:34
 */
@Slf4j
@Component
public class ConfirmConsumer {
    @RabbitListener(queues = ConfirmConfig.CONFIRM_QUEUE_NAME)
    public void receiveMessage(Message message){
        log.info("接收到的消息:{}", new String(message.getBody()));
    }
}
