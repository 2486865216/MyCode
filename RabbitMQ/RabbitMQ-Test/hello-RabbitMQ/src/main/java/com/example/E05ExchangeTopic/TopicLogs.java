package com.example.E05ExchangeTopic;

import com.example.Utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * author ye
 * createDate 2022/2/26  21:41
 * 生产者
 */
public class TopicLogs {

    static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMQUtil.getChannel();

        Map<String, String> map = new HashMap<>();
        map.put("quick.orange.rabbit", "被队列Q1、Q2接收到");
        map.put("lazy.orange.elephant", "被队列Q1、Q2接收到");
        map.put("quick.orange.fox", "被队列Q1接收到 ");
        map.put("lazy.brown.fox", "被队列Q2接收到 ");
        map.put("lazy.pink.rabbit", "虽然满足两个绑定但只被队列Q2接收一次");
        map.put("quick.brown.fox ", "不匹配任何绑定不会被任何队列接收到会被丢弃");
        map.put("quick.orange.male.rabbit", "是四个单词不匹配任何绑定会被丢弃 ");
        map.put("lazy.range.male.rabbit", "是四个单词但匹配Q2 ");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String message = (String) entry.getValue();
            String routKey = (String) entry.getKey();
            channel.basicPublish(EXCHANGE_NAME, routKey, null, message.getBytes("utf-8"));
            System.out.println(message);
        }
    }
}
