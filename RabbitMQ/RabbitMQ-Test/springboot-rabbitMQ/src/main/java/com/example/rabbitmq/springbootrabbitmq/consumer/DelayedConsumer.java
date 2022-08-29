package com.example.rabbitmq.springbootrabbitmq.consumer;

import com.example.rabbitmq.springbootrabbitmq.config.DelayedQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * author ye
 * createDate 2022/3/4  22:26
 * 基于插件的延迟
 */
@Slf4j
@Component
public class DelayedConsumer {
    //监听消息
    @RabbitListener(queues = DelayedQueueConfig.DELAYED_QUEUE)
    public void receiveDelayedMessage(Message message){
        String msg = new String(message.getBody());
        log.info("当前时间：{}，接受到delayed死信队列消息:{}",new Date(), msg);
    }
}
