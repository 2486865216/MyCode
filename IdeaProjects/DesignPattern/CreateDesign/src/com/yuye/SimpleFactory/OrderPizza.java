package com.yuye.SimpleFactory;

import java.util.Scanner;

/**
 * author ye
 * createDate 2022/1/26  13:40
 */
public class OrderPizza {
    SimpleFactory01 simpleFactory01;
    Pizza pizza = null;
    public void setSimpleFactory01(SimpleFactory01 simpleFactory01){
        String type = "";
        this.simpleFactory01 = simpleFactory01;
        do {
            type = getType();
            pizza = this.simpleFactory01.createPizza(type);
            if (pizza != null){
                pizza.prepara();
                pizza.pack();
            }else {
                System.out.println("Error");
                break;
            }
        }while (true);
    }
    public OrderPizza(SimpleFactory01 simpleFactory01){
        setSimpleFactory01(simpleFactory01);
    }
    public String getType(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Scanner type:");
        String s = scanner.nextLine();
        return s;
    }
}
