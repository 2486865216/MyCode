package com.example.E05Exchange;

import com.example.Utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;

import java.io.IOException;

/**
 * author ye
 * createDate 2022/2/25  21:58
 * 为此我们可以创建一个具有随机名称的队列，或者能让服务器为我们选择一个随机队列名称那就更好了。
 * 其次一旦我们断开了消费者的连接，队列将被自动删除。
 */
public class ATemporaryQueue {
    public static void main(String[] args) throws IOException {
        Channel channel = RabbitMQUtil.getChannel();
        String queue = channel.queueDeclare().getQueue();
        System.out.println(queue);
    }
}
