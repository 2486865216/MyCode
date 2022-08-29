package com.example.F06DeadQueue;

import com.example.Utils.RabbitMQUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * author ye
 * createDate 2022/3/1  21:31
 */
public class Consumer01 {
    //普通交换机
    public static final String NORMAL_EXCHANGE = "normal_exchange";
    //死信交换机
    public static final String DEAD_EXCHANGE = "dead_exchange";

    //普通队列
    public static final String NORMAL_QUEUE_NAME = "normal_queue";
    //死信队列
    public static final String DEAD_QUEUE_NAME = "dead_queue";

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMQUtil.getChannel();

        //声明交换机
        //普通
        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
        //死信
        channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);

        //声明队列
        //普通
        Map<String, Object> arguments = new HashMap<>();
        //过期时间,10s=10000ms
        //arguments.put("x-message-ttl",10000);
        //正常队列设置死信交换机
        arguments.put("x-dead-letter-exchange", DEAD_EXCHANGE);
        //设置死信RoutKey
        arguments.put("x-dead-letter-routing-key","lisi");
        //设置队列最大长度
        //arguments.put("x-max-length",5);

        channel.queueDeclare(NORMAL_QUEUE_NAME, false, false, false, arguments);
        //死信
        channel.queueDeclare(DEAD_QUEUE_NAME, false, false, false, null);

        //绑定交换机与队列
        //普通
        channel.queueBind(NORMAL_QUEUE_NAME, NORMAL_EXCHANGE , "zhangsan");
        //死信
        channel.queueBind(DEAD_QUEUE_NAME, DEAD_EXCHANGE, "lisi");

        System.out.println("等待接受消息..........");

        DeliverCallback deliverCallback = (consumerTag, message) -> {
            String msg = new String(message.getBody());
            if (msg.contains("5")){
                System.out.println(msg + " : 被拒绝的消息");
                channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
            }else {
                System.out.println("Consumer01接受的消息：   "+msg);
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            }
        };
        //开启手动应答
        channel.basicConsume(NORMAL_QUEUE_NAME, false, deliverCallback, consumerTag -> {});
    }
}
