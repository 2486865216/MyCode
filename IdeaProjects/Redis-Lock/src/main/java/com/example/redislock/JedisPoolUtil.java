package com.example.redislock;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
    private static volatile JedisPool jedisPool = null;

    public JedisPoolUtil() {
    }

    public static JedisPool getJedisPoolInstance(){
        if (jedisPool == null){
            synchronized (JedisPoolUtil.class){
                if (null == jedisPool){
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setMaxTotal(200);
                    poolConfig.setMaxIdle(32);
                    poolConfig.setMaxWaitMillis(100*1000);
                    poolConfig.setBlockWhenExhausted(true);
                    poolConfig.setTestOnBorrow(true);

                    jedisPool = new JedisPool(poolConfig, "192.168.229.131", 6379, 6000);
                }
            }
        }
        return jedisPool;
    }
    public static void release(JedisPool jedisPool, Jedis jedis){
        if (jedis != null){
            jedisPool.returnResource(jedis);
        }
    }
}
