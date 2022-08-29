package com.qiqi.Decorator;

/**
 * author ye
 * createDate 2022/2/6  11:52
 */
public class Soy extends DecoratorTest{
    public Soy(Drink drink) {
        super(drink);
        setDescribe("豆浆");
        setPrice(4.0f);
    }
}
