package com.example.Test;

import com.example.Bean.Car;
import com.example.Bean.Cat;
import com.example.Bean.Dog;
import com.example.Config.MyConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * author ye
 * createDate 2022/3/15  8:39
 */
public class MyTestOfLiveCycle {
    @Test
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfigOfLifeCycle.class);
        Car car = (Car) context.getBean("car");
        Cat cat = (Cat) context.getBean("cat");
        Dog dog = (Dog) context.getBean("dog");
        context.close();
    }

    @Test
    public void test2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfigOfLifeCycle.class);
        System.out.println("容器创建完成");
        context.close();

    }
}
