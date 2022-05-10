package com.qiqi.StrategyPattern;

/**
 * author ye
 * createDate 2022/2/9  14:56
 */
public class ToyDuck extends Duck{
    @Override
    public void display() {
        setFlyBehavior(new NoFly());
        quack();
        swimming();
        fly();
    }
}
