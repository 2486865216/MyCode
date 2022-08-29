package com.example.Bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * author ye
 * createDate 2022/3/15  10:13
 */
@Component
public class TestAware implements ApplicationContextAware, BeanNameAware {
    private ApplicationContext applicationContext;
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        System.out.println("传入的IOC : " + applicationContext);
    }

    public void setBeanName(String name) {
        System.out.println("当前Bean的名字: "+name);
    }
}
