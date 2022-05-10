package com.yuye.Factory;

/**
 * author ye
 * createDate 2022/1/26  14:01
 */
public class BJGreekPizza extends Pizza{
    @Override
    public void prepara() {
        setName("BJGreekPizza");
        System.out.println("BJGreekPizza is prepare");
    }
}
