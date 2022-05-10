package com.example.Bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * author ye
 * createDate 2022/3/15  9:59
 */
@Component
public class Boss {
    private Car car;

    //构造器要用的组件，都是从容器中获取
    public Boss(Car car) {
        this.car = car;
    }

    public Boss() {
    }

    public Car getCar() {
        return car;
    }
    //标注在方法，Spring容器创建当前对象，就会调用方法，完成赋值：
    //方法使用的参数，自定义类型的值从ioc容器中获取
    @Autowired
    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "car=" + car +
                '}';
    }
}
