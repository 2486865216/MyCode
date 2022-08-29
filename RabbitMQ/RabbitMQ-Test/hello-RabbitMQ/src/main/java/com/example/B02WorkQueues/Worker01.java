package com.example.B02WorkQueues;

import com.example.Utils.RabbitMQUtil;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;

/**
 * author ye
 * createDate 2022/2/24  17:01
 * 这是一个工作线程
 */
public class Worker01 {
    //队列名
    public static final String QUEUE_NAME = "hello";

    //接受消息
    public static void main(String[] args) throws IOException {
        Channel channel = RabbitMQUtil.getChannel();

        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("接收到的消息：" + new String(message.getBody()));
        };

        CancelCallback cancelCallback = consumerTag -> {
            System.out.println(consumerTag + "消费者取消消费");
        };
        System.out.println("C2等待接受消息.......");
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
    }
}
