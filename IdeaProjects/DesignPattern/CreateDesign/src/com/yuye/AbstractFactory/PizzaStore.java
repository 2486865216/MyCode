package com.yuye.AbstractFactory;

/**
 * author ye
 * createDate 2022/1/26  14:34
 */
public class PizzaStore {
    public static void main(String[] args) {
        new OrderPizza(new BJFactory());
    }
}
