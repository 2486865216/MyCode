package com.example.Bean;

import org.springframework.beans.factory.annotation.Value;

/**
 * author ye
 * createDate 2022/3/14  14:57
 */
public class Person {
    //使用@Value赋值；
    //1、基本数值
    //2、可以写SpEL;#{)
    //3、可以写${}；取出配置文件中的值（在运行环境变量里面的值）
    //@Value("zhangsan")
    @Value("${name}")
    private String name;
    @Value("#{20 -2}")
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
