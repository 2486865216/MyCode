package com.example.F06DeadQueue;

import com.example.Utils.RabbitMQUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * author ye
 * createDate 2022/3/1  21:31
 */
public class Consumer02 {
    //死信队列
    public static final String DEAD_QUEUE_NAME = "dead_queue";

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMQUtil.getChannel();

        System.out.println("等待接受消息..........");

        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("Consumer02接受的消息：   "+new String(message.getBody()));
        };

        channel.basicConsume(DEAD_QUEUE_NAME, true, deliverCallback, consumerTag -> {});
    }
}
