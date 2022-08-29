package com.example.C03MessageResponse;

import com.example.Utils.RabbitMQUtil;
import com.example.Utils.SleepUtil;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;

/**
 * author ye
 * createDate 2022/2/24  20:27
 * 消费者
 */
public class Worker01 {
    public static final String QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws IOException {
        Channel channel = RabbitMQUtil.getChannel();
        System.out.println("c1处理时间较短");

        DeliverCallback deliverCallback = (consumerTag, message) -> {
            //睡一秒
            SleepUtil.sleepTime(1);
            System.out.println("接受到的消息:" + new String(message.getBody(),"utf-8"));
            //手动应答
            /**
             * long deliveryTag,  消息的标记
             * boolean multiple   是否批量应答
             */
            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
        };

        CancelCallback cancelCallback = consumerTag -> {
            System.out.println(consumerTag + "消费者取消消费取消的回调");
        };
        //设置不公平分发
        //int prefetchCount = 1;
        //预取值是2
        int prefetchCount = 2;
        channel.basicQos(prefetchCount);
        //采用手动应答
        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME, autoAck, deliverCallback, cancelCallback);
    }
}
