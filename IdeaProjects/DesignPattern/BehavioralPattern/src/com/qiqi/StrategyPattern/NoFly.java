package com.qiqi.StrategyPattern;

/**
 * author ye
 * createDate 2022/2/9  14:55
 */
public class NoFly implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("不会飞");
    }
}
