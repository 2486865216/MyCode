package com.qiqi.StrategyPattern;

/**
 * author ye
 * createDate 2022/2/9  14:56
 */
public class BadFly implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("飞的不好");
    }
}
