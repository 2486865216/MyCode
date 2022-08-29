package com.qiqi.StrategyPattern;

/**
 * author ye
 * createDate 2022/2/9  14:47
 */
public abstract class Duck {

    FlyBehavior flyBehavior;
    public Duck() {
    }

    public abstract void display();
    public void quack(){
        System.out.println("嘎嘎");
    }
    public void swimming(){
        System.out.println("鸭子游泳");
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }
    public void fly(){
        flyBehavior.fly();
    }
}
