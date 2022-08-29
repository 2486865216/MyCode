package com.yuye.AbstractFactory;


/**
 * author ye
 * createDate 2022/1/26  14:01
 */
public class LDGreekPizza extends Pizza {
    @Override
    public void prepara() {
        setName("LDGreekPizza");
        System.out.println("LDGreekPizza is prepare");
    }
}
