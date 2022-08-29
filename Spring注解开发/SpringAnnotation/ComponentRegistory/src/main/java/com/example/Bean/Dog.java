package com.example.Bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * author ye
 * createDate 2022/3/15  8:54
 */
public class Dog {
    public Dog() {
        System.out.println("Dog Constructor");
    }

    //对象创建并赋值之后
    @PostConstruct
    public void init(){
        System.out.println("Dog init...");
    }

    //对象移除之前
    @PreDestroy
    public void destroy(){
        System.out.println("Dog destroy...");
    }
}
