package com.redis.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

public class JedisDemo {
    public static void main(String[] args) {
        //创建Jedis对象
        Jedis jedis = new Jedis("192.168.229.131", 6379);

        //测试
        String ping = jedis.ping();
        System.out.println(ping);
    }

    /**
     * 操作key
     */
    @Test
    public void test() {
        //创建Jedis对象
        Jedis jedis = new Jedis("192.168.229.131", 6379);
        jedis.set("name", "lucy");
        String name = jedis.get("name");
        System.out.println(name);

        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }
    }

    /**
     * 操作list
     */
    @Test
    public void test2() {
        //创建Jedis对象
        Jedis jedis = new Jedis("192.168.229.131", 6379);

        jedis.lpush("user", "lucy", "mary", "tom");

        List<String> user = jedis.lrange("user", 0, -1);

        for (String s : user) {
            System.out.println(s);
        }
    }

    /**
     * 操作set
     */
    @Test
    public void test3() {
        //创建Jedis对象
        Jedis jedis = new Jedis("192.168.229.131", 6379);

        jedis.sadd("key", "lucy", "mary");
        Set<String> key = jedis.smembers("key");
        for (String s : key) {
            System.out.println(s);
        }
    }

    /**
     * 操作hash
     */
    @Test
    public void test4() {
        //创建Jedis对象
        Jedis jedis = new Jedis("192.168.229.131", 6379);

        jedis.hset("users", "age", "20");
        String hget = jedis.hget("users", "age");
        System.out.println(hget);
    }

    /**
     * 操作Zset
     */
    @Test
    public void test5() {
        //创建Jedis对象
        Jedis jedis = new Jedis("192.168.229.131", 6379);

        jedis.zadd("zset", 200, "hello");
        List<String> zset = jedis.zrange("zset", 0, -1);
        for (String s : zset) {
            System.out.println(s);
        }
        jedis.flushDB();
    }
}
