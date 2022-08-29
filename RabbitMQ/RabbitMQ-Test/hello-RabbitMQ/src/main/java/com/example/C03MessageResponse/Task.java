package com.example.C03MessageResponse;

import com.example.Utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.Scanner;

/**
 * author ye
 * createDate 2022/2/24  20:22
 * 消息在手动应答时是不丢失、放回队列中重新消费
 */
public class Task {
    public static final String QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws IOException {
        Channel channel = RabbitMQUtil.getChannel();
        //开启发布确认
        channel.confirmSelect();
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();
            //设置生产者发送消息为持久化
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
            System.out.println("消息发送成功：" + message);
        }
    }
}
