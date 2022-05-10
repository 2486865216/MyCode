package com.example.H10Test;

import com.example.Utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/**
 * author ye
 * createDate 2022/3/14  11:38
 */
public class Consumer {
    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.229.141");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("123");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("收到的消息为：" + new String(message.getBody()));
        };
        channel.basicConsume(Producer.QUEUE_NAME, deliverCallback, consumerTag -> {});
    }
}
