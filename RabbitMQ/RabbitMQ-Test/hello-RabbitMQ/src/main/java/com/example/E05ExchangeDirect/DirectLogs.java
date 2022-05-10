package com.example.E05ExchangeDirect;

import com.example.Utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;

import java.util.Scanner;

/**
 * author ye
 * createDate 2022/2/26  21:41
 */
public class DirectLogs {

    static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMQUtil.getChannel();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();
            channel.basicPublish(EXCHANGE_NAME, "error", null, message.getBytes("utf-8"));
            System.out.println("生产者发送消息:  " + message);
        }
    }
}
