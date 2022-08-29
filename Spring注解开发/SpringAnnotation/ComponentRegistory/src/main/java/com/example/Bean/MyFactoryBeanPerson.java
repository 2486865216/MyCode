package com.example.Bean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * author ye
 * createDate 2022/3/15  8:24
 */
public class MyFactoryBeanPerson implements FactoryBean<Person> {
    public Person getObject() throws Exception {
        System.out.println("MyFactoryBeanPerson.............");
        return new Person();
    }

    public Class<?> getObjectType() {
        return Person.class;
    }

    //是单例？
    //true:这个bean是单实例，在容器中保存一份
    //false:多实例，每次获取都会创建一个新的bean;
    public boolean isSingleton() {
        return true;
    }
}
