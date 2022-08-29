package com.example.A01Hello;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * author ye
 * createDate 2022/2/24  11:40
 * 生产者
 */
public class Producer {
    //队列
    public static final String QUEUE_NAME = "hello";

    //发消息
    public static void main(String[] args){
        //创建一个连接工厂
        ConnectionFactory factory = new com.rabbitmq.client.ConnectionFactory();
        //工厂的IP
        factory.setHost("192.168.229.130");
        //用户名
        factory.setUsername("admin");
        //密码
        factory.setPassword("123");

        ///创建连接
        try {
            Connection connection = factory.newConnection();

            //获取信道
            Channel channel = connection.createChannel();

            /**
             * 生成一个队列
             * String queue,                    队列名
             * boolean durable,                 是否持久化(默认内存)
             * boolean exclusive,               该队列是否只供一个消费者进行消费是否进行消息共享，true可以多个消费者消贵
             * boolean autoDelete,              是否自动删除最后一个消费者端开连接以后该队一句是否自动删除true自动删除false不自动删除
             * Map<String, Object> arguments    其它参数
             */
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            //发消息
            String message = "Hello Word";

            /**
             * 发送一个消费
             * String var1,             发送到哪个交换机
             * String var2,             路由的Key值是哪个,本次是队列的名称
             * BasicProperties var3,    其它参数信息
             * byte[] var4              发送消息的消息体
             */
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("消息发送完毕!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
