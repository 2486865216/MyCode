package com.yuye.AbstractFactory;


/**
 * author ye
 * createDate 2022/1/26  14:01
 */
public class LDCheesePizza extends Pizza {
    @Override
    public void prepara() {
        setName("LDCheesePizza");
        System.out.println("LDCheesePizza is prepare");
    }
}
