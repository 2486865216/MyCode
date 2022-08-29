package com.example.rabbitmq.springbootrabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * author ye
 * createDate 2022/3/5  21:41
 * 交换机确认回调方法
 *  1.发消息交换机接收到了回调
 *      1.1 correlationData保存回调消息的ID及相关信息
 *      1.2交换机收到消息ack=true
 *      1.3 cause,nu11
 *  2.发消息交换机接收失败了回调
 *      2.1 correlationData保存回调消息的ID及相关信息
 *      2.2交换机收到消息ack=fa1se
 *      2.3 cause失败的原因
 */
@Component
@Slf4j
public class MyConfirmCallBack implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback{

    //注入
    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String id = correlationData != null ? correlationData.getId() : "";
        if (ack){
            log.info("交换机已收到ID为：{}的消息",id);
        }
        else {
            log.info("交换机未收到ID为：{}消息，原因是：{}", id, cause);
        }
    }
    //可以在当消息传递过程中不可达目的地时将消息返回给生产者
    //只有失败的才回退
    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        log.info("消息{},被交换机{},回退了，原因是{}",new String(returnedMessage.getMessage().getBody()),returnedMessage.getExchange(), returnedMessage.getReplyText());
    }
}
