package com.yuye.Factory;

/**
 * author ye
 * createDate 2022/1/26  14:08
 */
public class LDOrderPizzaFactory extends OrderPizza{
    @Override
    Pizza createPizza(String type) {
        Pizza pizza = null;
        if (type.equals("greek")){
            pizza = new LDGreekPizza();
        }else if (type.equals("cheese")){
            pizza = new LDCheesePizza();
        }
        return pizza;
    }
}
