package com.example.A01Hello;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * author ye
 * createDate 2022/2/24  12:07
 * 消费者
 */
public class Consumer {
    //队列名
    public static final String QUEUE_NAME = "hello";
    //接受消息
    public static void main(String[] args) {
        //创建连接工厂
        ConnectionFactory factory = new com.rabbitmq.client.ConnectionFactory();
        //工厂的IP
        factory.setHost("192.168.229.130");
        //用户名
        factory.setUsername("admin");
        //密码
        factory.setPassword("123");

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            //消费者成功消费的回调
            DeliverCallback deliverCallback = (consumer, message) -> {
                System.out.println(consumer);
                System.out.println(new String(message.getBody()));
            };

            //消费者取消消费的回调
            CancelCallback cancelCallback = var1 -> {
                System.out.println("消息消费被中断");
            };
            /**
             * 消费者消费消息
             * String var1,          消费哪个队列
             * boolean var2,         消费成功之后是否要自动应答,true代表的自动应答,false代表手动应答
             * DeliverCallback var3,  消费者成功消费的回调
             * CancelCallback var4   消费者取消消费的回调
             */
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
