package com.yuye.AbstractFactory;

import com.sun.xml.internal.bind.v2.model.core.ID;

/**
 * author ye
 * createDate 2022/1/26  14:27
 */
//工厂子类
public class BJFactory implements AbstractFactoryTest{
    @Override
    public Pizza createPizza(String type) {
        Pizza pizza = null;
        if (type.equals("greek")){
            pizza = new BJGreekPizza();
        }else if (type.equals("cheese")){
            pizza = new BJCheesePizza();
        }
        return pizza;
    }
}
