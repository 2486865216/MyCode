package com.redis.jedis;

import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * 1、输入手机号，点击发送后随机生成6位数字码，2分钟有效
 * 2、输入验证码，点击验证，返回成功或失败。
 * 3、每个手机号每天只能输入3次
 */
public class JedisExample {
    public static void main(String[] args) {
        verifyCode("18577787964");
        //getRedisCode("18577787964","008614");
    }

    //生成验证码
    public static String getCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int randomInt = random.nextInt(10);
            code.append(randomInt);
        }
        return code.toString();
    }

    //验证码放到redis，设置过期时间，每个手机每天只能发送三次
    public static void verifyCode(String phone) {
        //创建Jedis对象
        Jedis jedis = new Jedis("192.168.229.131", 6379);

        //手机发送次数key
        String countKey = "VerifyCode" + phone + ":count";
        //验证码key
        String codeKey = "VerifyCode" + phone + ":code";

        //每个手机每天只能发送三次
        String count = jedis.get(countKey);
        if (count == null) {
            jedis.setex(countKey, 24 * 60 * 60, "1");
        } else if (Integer.parseInt(count) <= 2) {
            jedis.incr(countKey);
        } else if (Integer.parseInt(count) > 2) {
            System.out.println("今天发送的次数已经超过三次！");
            jedis.close();
            return;
        }

        //验证码放到redis
        String vcode = getCode();
        jedis.setex(codeKey, 120, vcode);
        jedis.close();
    }

    //校验验证码
    public static void getRedisCode(String phone, String code) {
        //创建Jedis对象
        Jedis jedis = new Jedis("192.168.229.131", 6379);

        String codeKey = "VerifyCode" + phone + ":code";
        String redisCode = jedis.get(codeKey);

        if (redisCode.equals(code)) {
            System.out.println("Success!");
        } else {
            System.out.println("Failed!");
        }

        jedis.close();
    }
}
