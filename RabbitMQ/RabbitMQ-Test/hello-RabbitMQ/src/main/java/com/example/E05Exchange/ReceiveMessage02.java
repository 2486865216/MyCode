package com.example.E05Exchange;

import com.example.Utils.RabbitMQUtil;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * author ye
 * createDate 2022/2/25  21:59
 * 消息接收者02
 */
public class ReceiveMessage02 {
    //交换机的名称
    public static final String EXCHANGE_NAME = "loge";
    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMQUtil.getChannel();
        //声明一个交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        //声明一个临时队列
        //当消费者断开与队列的连接的时候队列就自动删除
        String queue = channel.queueDeclare().getQueue();

        //绑定交换机与队列
        channel.queueBind(queue, EXCHANGE_NAME, "");
        System.out.println("消息接收者02等待接收消息，把接收到的消息打印在屏幕上............");

        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("消息接收者02打印接收到的消息:" + new String(message.getBody(),"utf-8"));
        };

        CancelCallback cancelCallback = consumerTag -> {
        };
        channel.basicConsume(queue, false, deliverCallback, cancelCallback);
    }
}
