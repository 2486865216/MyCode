package com.qiqi.Decorator;

/**
 * author ye
 * createDate 2022/2/6  11:52
 */
//牛奶调味品
public class Milk extends DecoratorTest{
    public Milk(Drink drink) {
        super(drink);
        setDescribe("牛奶");
        setPrice(5.0f);
    }
}
