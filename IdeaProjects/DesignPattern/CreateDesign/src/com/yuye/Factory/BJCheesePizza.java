package com.yuye.Factory;

/**
 * author ye
 * createDate 2022/1/26  14:01
 */
public class BJCheesePizza extends Pizza{
    @Override
    public void prepara() {
        setName("BJCheesePizza");
        System.out.println("BJCheesePizza is prepare");
    }
}
