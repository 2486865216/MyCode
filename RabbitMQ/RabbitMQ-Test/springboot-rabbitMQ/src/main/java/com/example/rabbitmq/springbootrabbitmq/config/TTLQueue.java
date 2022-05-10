package com.example.rabbitmq.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * author ye
 * createDate 2022/3/3  21:22
 */
@Configuration
public class TTLQueue {
    //普通交换机名称
    public static final String X_EXCHANGE = "X";
    //死信交换机名称
    public static final String Y_EXCHANGE = "Y";
    //普通队列名称
    public static final String QUEUE_A = "QA";
    public static final String QUEUE_B = "QB";
    //死信队列名称
    public static final String QUEUE_D = "QD";



    public static final String QUEUE_C = "QC";

    @Bean("queueC")
    public Queue queueC() {
        Map<String, Object> arguments = new HashMap<>();
        //设置死信交换机
        arguments.put("x-dead-letter-exchange",Y_EXCHANGE);
        //设置死信routing key
        arguments.put("x-dead-letter-routing-key","YD");
        return QueueBuilder.durable(QUEUE_C).withArguments(arguments).build();
    }

    @Bean
    public Binding queueCBinding(@Qualifier("queueC") Queue queueC,
                                 @Qualifier("xExchange") DirectExchange exchange){
        return BindingBuilder.bind(queueC).to(exchange).with("XC");
    }

    //声明X交换机
    @Bean("xExchange")
    public DirectExchange xExchange(){
        return new DirectExchange(X_EXCHANGE);
    }
    //声明Y交换机
    @Bean("yExchange")
    public DirectExchange yExchange(){
        return new DirectExchange(X_EXCHANGE);
    }

    //声明普通队列TTL为10s
    @Bean("queueA")
    public Queue queueA() {
        Map<String, Object> arguments = new HashMap<>();
        //设置TTL  单位ms
        arguments.put("x-message-ttl",10000);
        //设置死信交换机
        arguments.put("x-dead-letter-exchange",Y_EXCHANGE);
        //设置死信routing key
        arguments.put("x-dead-letter-routing-key","YD");
        return QueueBuilder.durable(QUEUE_A).withArguments(arguments).build();
    }
    //声明普通队列TTL为40s
    @Bean("queueB")
    public Queue queueB() {
        Map<String, Object> arguments = new HashMap<>();
        //设置TTL  单位ms
        arguments.put("x-message-ttl",40000);
        //设置死信交换机
        arguments.put("x-dead-letter-exchange",Y_EXCHANGE);
        //设置死信routing key
        arguments.put("x-dead-letter-routing-key","YD");
        return QueueBuilder.durable(QUEUE_B).withArguments(arguments).build();
    }
    //声明死信队列
    @Bean("queueD")
    public Queue queueD() {
        return QueueBuilder.durable(QUEUE_D).build();
    }

    //绑定
    @Bean
    public Binding queueABindingX(@Qualifier("queueA") Queue queueA, @Qualifier("xExchange") DirectExchange xExchange){
        return BindingBuilder.bind(queueA).to(xExchange).with("XA");
    }

    //绑定
    @Bean
    public Binding queueBBindingY(@Qualifier("queueB") Queue queueB, @Qualifier("xExchange") DirectExchange xExchange){
        return BindingBuilder.bind(queueB).to(xExchange).with("XB");
    }

    //绑定
    @Bean
    public Binding queueDBindingY(@Qualifier("queueD") Queue queueD, @Qualifier("yExchange") DirectExchange yExchange){
        return BindingBuilder.bind(queueD).to(yExchange).with("YD");
    }
}
