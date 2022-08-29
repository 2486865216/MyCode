package com.example.Config;

import com.example.Bean.Person;
import com.example.Service.ServiceTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.awt.*;

/**
 * author ye
 * createDate 2022/3/14  14:56
 */
@Configuration
//@ComponentScan(value = "com.example", excludeFilters = {
//        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class, Service.class})
//})
@ComponentScan(value = "com.example", includeFilters = {
        /*@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class}),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {ServiceTest.class}),*/
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {MyFilter.class})
}, useDefaultFilters = false)
//@ComponentScan value:指定要扫描的包
//excludeFilters Filter[]:指定扫描的时候按照什么规则排除那些组件
//includeFilters=Filter[]:指定扫描的时候只需要包含哪些组件

//FilterType.ANNOTATION:按照注解
//FilterType.ASSIGNABLE_TYPE:按照给定的类型；
//FilterType.ASPECTJ:使用ASPECTJ表达式
//FilterType.REGEX:使用正则指定
//FilterType.CUSTOM:使用自定义规则
public class MyConfig {
    @Bean("person")
    //给容器中注册一个Bean;类型为返回值的类型，id默认是用方法名作为id
    public Person person(){
        return new Person();
    }
}
