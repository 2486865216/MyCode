package com.yuye.AbstractFactory;

import java.lang.reflect.Type;
import java.util.Scanner;

/**
 * author ye
 * createDate 2022/1/26  14:30
 */
public class OrderPizza {
    AbstractFactoryTest abstractFactoryTest;

    public OrderPizza(AbstractFactoryTest abstractFactoryTest) {
        System.out.println("抽象工厂模式");
        setAbstractFactoryTest(abstractFactoryTest);
    }

    public void setAbstractFactoryTest(AbstractFactoryTest abstractFactoryTest) {
        this.abstractFactoryTest = abstractFactoryTest;
        Pizza pizza = null;
        String type = "";
        do {
            type = getType();
            //可能是北京工厂，也可能是伦敦工厂
            pizza = abstractFactoryTest.createPizza(type);
            if (pizza != null){
                pizza.prepara();
                pizza.pack();
            }else{
                System.out.println("Error");
                break;
            }
        }while (true);
    }
    public String getType(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Scanner type:");
        String s = scanner.nextLine();
        return s;
    }
}
