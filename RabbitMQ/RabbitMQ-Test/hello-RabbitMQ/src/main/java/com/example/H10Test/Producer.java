package com.example.H10Test;

import com.example.Utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;

/**
 * author ye
 * createDate 2022/3/14  11:38
 */
public class Producer {
    public static final String QUEUE_NAME = "marrior_hello_world";
    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMQUtil.getChannel();
        String message = "cluster test";
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println("消息发送完毕！");
    }
}
