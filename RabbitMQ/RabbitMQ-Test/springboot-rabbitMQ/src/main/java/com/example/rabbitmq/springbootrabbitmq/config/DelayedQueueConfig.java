package com.example.rabbitmq.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * author ye
 * createDate 2022/3/4  22:12
 */
@Configuration
public class DelayedQueueConfig {
    //队列
    public static final String DELAYED_QUEUE = "delayed_queue";
    //交换机
    public static final String DELAYED_EXCHANGE = "delayed_exchange";
    //routingKey
    public static final String DELAYED_ROUTINGKEY = "delayed_routing";

    @Bean
    public Queue queue(){
        return new Queue(DELAYED_QUEUE);
    }

    //声明交换机
    @Bean
    public CustomExchange delayedExchange(){
        /**
         * String name,         交换机的名称
         * String type,         交换机的类型
         * boolean durable,     是否需要持久化
         * boolean autoDelete,  是否需要自动删除
         * Map<String, Object>   其它参数
         */
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-delayed-type","direct");
        return new CustomExchange(DELAYED_EXCHANGE, "x-delayed-message", false, false, arguments);
    }

    @Bean
    public Binding binding(@Qualifier("queue") Queue queue,
                           @Qualifier("delayedExchange") CustomExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(DELAYED_ROUTINGKEY).noargs();

    }
}
