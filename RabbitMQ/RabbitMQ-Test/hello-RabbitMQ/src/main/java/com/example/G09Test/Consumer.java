package com.example.G09Test;


import com.example.Utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * author ye
 * createDate 2022/3/14  9:01
 */
public class Consumer {
    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMQUtil.getChannel();
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("收到的消息为：" + new String(message.getBody()));
        };
        channel.basicConsume(Producer.QUEUE_NAME, deliverCallback, consumerTag -> {});
    }
}
