package com.qiqi.Decorator;

/**
 * author ye
 * createDate 2022/2/6  11:50
 */
//具体的Decorator，这里就是调味品
public class Chocolate extends DecoratorTest{
    public Chocolate(Drink drink) {
        super(drink);
        setDescribe("巧克力");
        setPrice(3.0f);//调味品价格
    }
}
