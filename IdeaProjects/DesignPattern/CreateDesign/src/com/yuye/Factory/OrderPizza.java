package com.yuye.Factory;

import java.util.Scanner;

/**
 * author ye
 * createDate 2022/1/26  13:40
 */
public abstract class OrderPizza {
    //定义一个抽象方法，createPizza，让各个工厂类自己实现
    abstract Pizza createPizza(String type);
    public OrderPizza(){
        Pizza pizza = null;
        String type;
        do {
            type = getType();
            pizza = createPizza(type);//有工厂子类完成
            pizza.prepara();
            pizza.pack();
        }while (true);
    }
    public String getType(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Scanner type:");
        String s = scanner.nextLine();
        return s;
    }
}
