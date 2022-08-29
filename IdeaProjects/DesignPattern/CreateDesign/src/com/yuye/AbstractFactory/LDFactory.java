package com.yuye.AbstractFactory;

/**
 * author ye
 * createDate 2022/1/26  14:29
 */
//工厂子类
public class LDFactory implements AbstractFactoryTest{
    @Override
    public Pizza createPizza(String type) {
        Pizza pizza = null;
        if (type.equals("greek")){
            pizza = new LDGreekPizza();
        }else if (type.equals("cheese")){
            pizza = new LDCheesePizza();
        }
        return pizza;
    }
}
