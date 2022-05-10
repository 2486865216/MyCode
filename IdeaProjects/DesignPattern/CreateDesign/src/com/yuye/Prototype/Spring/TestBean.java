package com.yuye.Prototype.Spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * author ye
 * createDate 2022/1/27  9:54
 */
public class TestBean {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext
                ("com/yuye/Prototype/Spring/bean.xml");
        Object user1 = context.getBean("user");
        System.out.println(user1);

        Object user2 = context.getBean("user");
        System.out.println(user1==user2);
    }
}
