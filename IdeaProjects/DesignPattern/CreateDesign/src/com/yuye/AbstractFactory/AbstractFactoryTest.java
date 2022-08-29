package com.yuye.AbstractFactory;

//一个抽象接口的抽象层
public interface AbstractFactoryTest {
    //让下面的工厂子类来具体实现
    public Pizza createPizza(String type);
}
