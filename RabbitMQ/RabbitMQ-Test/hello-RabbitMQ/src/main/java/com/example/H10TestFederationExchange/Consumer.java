package com.example.H10TestFederationExchange;

import com.rabbitmq.client.*;

/**
 * author ye
 * createDate 2022/3/14  17:03
 */
public class Consumer {
    public static final String EXCHANGE_NAME = "fed_exchange";
    public static final String QUEUE_NAME = "fed_queue";
    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.229.141");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("123");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        channel.queueDeclare(QUEUE_NAME, false, false, false ,null);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");
    }
}
