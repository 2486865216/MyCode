package com.example.F06DeadQueue;

import com.example.Utils.RabbitMQUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

/**
 * author ye
 * createDate 2022/3/1  21:47
 * 生产者
 */
public class Producer {
    public static final String NORMAL_EXCHANGE = "normal_exchange";

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMQUtil.getChannel();
        //设置TTL time to live
        //AMQP.BasicProperties properties =
        //        new AMQP.BasicProperties()
        //                .builder()
        //                .expiration("10000")
        //                .build();

        for (int i = 0; i < 10; i++) {
            String info = "info : " + i;
            channel.basicPublish(NORMAL_EXCHANGE, "zhangsan", null, info.getBytes());
        }
    }
}
