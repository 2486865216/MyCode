package com.example.E05Exchange;

import com.example.Utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;

import java.util.Scanner;

/**
 * author ye
 * createDate 2022/2/25  22:08
 */
public class Task {
    //交换机的名称
    public static final String EXCHANGE_NAME = "loge";
    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMQUtil.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            String message = scanner.next();
            channel.basicPublish(EXCHANGE_NAME,"",null, message.getBytes("utf-8"));
            System.out.println("生产者发送消息: " + message);
        }
    }
}
