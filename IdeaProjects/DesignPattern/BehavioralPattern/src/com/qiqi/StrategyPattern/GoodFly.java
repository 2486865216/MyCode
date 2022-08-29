package com.qiqi.StrategyPattern;

/**
 * author ye
 * createDate 2022/2/9  14:56
 */
public class GoodFly implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("飞的好");
    }
}
