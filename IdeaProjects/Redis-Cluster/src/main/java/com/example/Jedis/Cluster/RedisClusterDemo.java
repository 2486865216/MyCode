package com.example.Jedis.Cluster;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.Set;

/**
 * 演示Redis集群操作
 */
public class RedisClusterDemo {
    public static void main(String[] args) {
        //创建对象
        HostAndPort hostAndPort = new HostAndPort("192.168.229.131", 6380);
        JedisCluster jedisCluster = new JedisCluster(hostAndPort);

        //进行操作
        jedisCluster.set("key1", "value1");

        String key1 = jedisCluster.get("key1");
        System.out.println(key1);

        jedisCluster.close();
    }
}
