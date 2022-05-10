package com.example.Utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * author ye
 * createDate 2022/2/24  16:56
 */
public class RabbitMQUtil {
    public static Channel getChannel(){
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //工厂的IP
        factory.setHost("192.168.229.130");
        //用户名
        factory.setUsername("admin");
        //密码
        factory.setPassword("123");

        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        Channel channel = null;
        try {
            channel = connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return channel;
    }
}
