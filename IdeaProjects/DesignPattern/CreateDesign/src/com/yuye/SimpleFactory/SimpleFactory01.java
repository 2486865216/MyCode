package com.yuye.SimpleFactory;


/**
 * 简单工厂
 * author ye
 * createDate 2022/1/26  12:43
 */
public class SimpleFactory01 {
    public Pizza createPizza(String type){
        Pizza pizza = null;
        System.out.println("使用简单工厂模式");
        if (type.equals("greek")){
            pizza = new GreekPizza();
            pizza.setName("Greek");
        }else if (type.equals("cheese")){
            pizza = new CheesePizza();
            pizza.setName("Cheese");
        }
        return pizza;
    }
}
