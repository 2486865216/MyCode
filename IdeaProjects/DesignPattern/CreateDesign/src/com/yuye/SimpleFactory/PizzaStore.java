package com.yuye.SimpleFactory;

/**
 * author ye
 * createDate 2022/1/26  13:46
 */
public class PizzaStore {
    public static void main(String[] args) {
        new OrderPizza(new SimpleFactory01());
    }
}
