package com.example.G09Test;

import com.example.Utils.RabbitMQUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

import java.util.HashMap;
import java.util.Map;

/**
 * author ye
 * createDate 2022/3/14  8:48
 */
public class Producer {
    public static final String QUEUE_NAME = "hello-world-09";
    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMQUtil.getChannel();
        //官方允许是0-255之间，此处设置10允许优化级范围为0-10，不要设置过大浪费CPU与内存
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-max-priority", 10);
        channel.queueDeclare(QUEUE_NAME, false, false, true, arguments);

        for (int i = 0; i < 10; i++) {
            String message = "info:" + i;
            if (i == 5){
                AMQP.BasicProperties properties =
                        new AMQP.BasicProperties().builder().priority(5).build();
                channel.basicPublish("", QUEUE_NAME, properties, message.getBytes());
            }else {
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            }
        }
        System.out.println("消息发送完毕！");
    }
}
