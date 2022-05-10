package com.qiqi.Decorator;

/**
 * author ye
 * createDate 2022/2/6  11:39
 */
public class Coffee extends Drink{
    @Override
    public float cost() {
        return super.getPrice();
    }
}
