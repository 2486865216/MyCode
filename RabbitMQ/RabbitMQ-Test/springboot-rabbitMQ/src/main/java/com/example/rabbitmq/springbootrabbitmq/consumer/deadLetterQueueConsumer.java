package com.example.rabbitmq.springbootrabbitmq.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * author ye
 * createDate 2022/3/3  22:03
 * 消费者
 */
@Slf4j
@Component
public class deadLetterQueueConsumer {
    //接受消息
    @RabbitListener(queues = "QD")
    public void receiceD(Message message) throws Exception{
        String msg = new String(message.getBody());
        log.info("当前时间：{}，接受到死信队列消息:{}",new Date(), msg);
    }
}
