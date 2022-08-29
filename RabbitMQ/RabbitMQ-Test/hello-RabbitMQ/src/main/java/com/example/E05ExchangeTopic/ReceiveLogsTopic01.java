package com.example.E05ExchangeTopic;

import com.example.Utils.RabbitMQUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * author ye
 * createDate 2022/2/26  21:34
 */
public class ReceiveLogsTopic01 {

    static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMQUtil.getChannel();
        //声明一个交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        //声明一个队列
        String name = "Q1";
        channel.queueDeclare(name, false, false, false, null);

        channel.queueBind(name, EXCHANGE_NAME, "*.orange.*");
        System.out.println("等待接受消息..............");
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println(new String(message.getBody(),"utf-8"));
            System.out.println("接收队列 : " + name + " ; ;绑定键 : " + message.getEnvelope().getRoutingKey());
            System.out.println("======================================");
        };

        channel.basicConsume(name, true, deliverCallback, consumerTag -> {});
    }
}
