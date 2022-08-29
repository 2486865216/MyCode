package com.example.D04PublicConfirm;

import com.example.Utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;

import java.util.UUID;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * author ye
 * createDate 2022/2/24  22:16
 * 发布确认模式
 *  1，单个确认
 *  2. 批量确认
 *  3. 异步确认
 */
public class ConfirmMessage {
    public static final int MESSAGE_COUNT = 1000;
    public static void main(String[] args) throws Exception{
        //1，单个确认
        //singlePublishConfirmMessage();//发布1000条数据的时间:  2192ms
        //2. 批量确认
        //batchPublishConfirmMessage();//发布1000条数据的时间:  110ms
        //3. 异步确认
        asyncPublishConfirmMessage();//发布1000条数据的时间:  65ms
    }
    //1，单个确认
    public static void singlePublishConfirmMessage() throws Exception{
        Channel channel = RabbitMQUtil.getChannel();
        channel.confirmSelect();
        String name = UUID.randomUUID().toString();
        channel.queueDeclare(name, false, false, false, null);
        long startTime = System.currentTimeMillis();
        //批量发消息
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("", name, null, message.getBytes());
            //单个确认
            boolean flag = channel.waitForConfirms();
            if (flag){
                System.out.println("消息发布成功!");
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("发布"+MESSAGE_COUNT+"条数据的时间:  " + (endTime - startTime) + "ms");
    }
    //2. 批量确认
    public static void batchPublishConfirmMessage() throws Exception{
        Channel channel = RabbitMQUtil.getChannel();
        channel.confirmSelect();
        String name = UUID.randomUUID().toString();
        channel.queueDeclare(name, false, false, false, null);
        long startTime = System.currentTimeMillis();
        //批量确认消息大小
        int batchSize = 100;
        //批量发消息
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("", name, null, message.getBytes());

            if (i % batchSize == 0){
                boolean flag = channel.waitForConfirms();
                if (flag){
                    System.out.println("消息发布成功!");
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("发布"+MESSAGE_COUNT+"条数据的时间:  " + (endTime - startTime) + "ms");
    }
    //3. 异步确认
    public static void asyncPublishConfirmMessage() throws Exception{
        Channel channel = RabbitMQUtil.getChannel();
        channel.confirmSelect();
        String name = UUID.randomUUID().toString();
        channel.queueDeclare(name, false, false, false, null);

        /**
         * 线程安全有序的一个哈希表适用于高并发的情沉下
         * 轻松的将序号与消息进行关联
         * 轻松批量删除条目只要给到序号
         * 支持高并发（多线程）
         */
        ConcurrentSkipListMap<Long, String> outStandingConfirm = new ConcurrentSkipListMap<>();
        //准备消息的监听器
        //消息确认成功
        ConfirmCallback ackCallback = (deliveryTag, multiple) -> {
            //删除已确认的消息,剩下的就是未确认的消息
            if (multiple){
                ConcurrentNavigableMap<Long, String> confirmed = outStandingConfirm.headMap(deliveryTag);
                confirmed.clear();
            }else {
                outStandingConfirm.remove(deliveryTag);
            }
            System.out.println("确认的消息:" + deliveryTag);
        };
        //消息确认失败
        ConfirmCallback nackCallback = (deliveryTag, multiple) -> {
            String message = outStandingConfirm.get(deliveryTag);
            System.out.println("未确认消息："+message+"::::::未确认的消息Tag:" + deliveryTag);
        };
        channel.addConfirmListener(ackCallback, nackCallback);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("", name, null, message.getBytes());
            //记录所有发送的消息
            outStandingConfirm.put(channel.getNextPublishSeqNo(), message);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("发布"+MESSAGE_COUNT+"条数据的时间:  " + (endTime - startTime) + "ms");
    }
}
