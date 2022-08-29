package com.qiqi.StrategyPattern;

/**
 * author ye
 * createDate 2022/2/9  14:56
 */
public class PekigDuck extends Duck{
    @Override
    public void display() {
        setFlyBehavior(new BadFly());
        quack();
        swimming();
        fly();
    }
}
