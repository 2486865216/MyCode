package com.example.redislock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.List;

public class SocKill_redis {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.229.131",6379);
        System.out.println(jedis.ping());
        jedis.close();
    }
    //秒杀过程
    public static boolean doSecKill(String userid, String prodid){
        //1.userid,prodid非空判断
        if (userid == null || prodid == null){
            return false;
        }

        //2.连接redis
        //Jedis jedis = new Jedis("192.168.229.131",6379);

        //通过连接池获取jedis对象
        JedisPool jedisPoolInstance = JedisPoolUtil.getJedisPoolInstance();
        Jedis jedis = jedisPoolInstance.getResource();

        //3.拼接key
        //库存key
        String kcKey = "sk:"+prodid+":qt";
        //秒杀成功key
        String userKey = "sk:0101:user";

        //监视库存
        jedis.watch(kcKey);

        //4.获取库存，如果库存null，秒杀未开始
        String kc = jedis.get(kcKey);
        if (kc == null){
            System.out.println("秒杀未开始!");
            jedis.close();
            return false;
        }

        //5.判断用户是否重复秒杀
        if (jedis.sismember(userKey, userid)){
            System.out.println("已经秒杀成功，不能重复秒杀！");
            jedis.close();
            return false;
        }

        //6.判断，如果商品数量，库存数量小于1，秒杀结束
        if (Integer.parseInt(kc) <= 0){
            System.out.println("秒杀已经结束！");
            jedis.close();
            return false;
        }

        //7.秒杀过程
        //使用事务
        Transaction multi = jedis.multi();

        //组队操作
        multi.decr(kcKey);
        multi.sadd(userKey, userid);

        //执行
        List<Object> result = multi.exec();

        if (result == null || result.size() == 0){
            System.out.println("秒杀失败！");
            jedis.close();
            return false;
        }

        //库存 -1
        //jedis.decr(kcKey);

        //把秒杀成功用户添加清单里面
        //jedis.sadd(userKey, userid);


        System.out.println("秒杀成功!");
        jedis.close();
        return true;
    }
}
