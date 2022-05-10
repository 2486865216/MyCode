package com.Dancebytes.spring5.test;

import com.Dancebytes.spring5.config.TxConfig;
import com.Dancebytes.spring5.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestBook {
    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        UserService service = context.getBean("userService",UserService.class);
        service.accontMoney();
    }
    @Test
    public void test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        UserService service = context.getBean("userService",UserService.class);
        service.accontMoney();
    }
    @Test
    public void test3(){
        ApplicationContext context = new AnnotationConfigApplicationContext(TxConfig.class);
        UserService service = context.getBean("userService",UserService.class);
        service.accontMoney();
    }
}
