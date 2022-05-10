package com.qiqi.spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * author ye
 * createDate 2022/2/7  13:26
 */
public class TestSpring {
    @Test
    public void test() {
        ApplicationContext context = new
                ClassPathXmlApplicationContext("com/qiqi/spring/bean.xml");
        User user = (User) context.getBean("user");
        System.out.println(user);
    }
}
