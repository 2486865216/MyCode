package com.qiqi.Decorator;

/**
 * author ye
 * createDate 2022/2/6  11:44
 */
public class DecoratorTest extends Drink{
    //之前添加的咖啡和调料
    //被装饰者
    private Drink drink;

    public DecoratorTest(Drink drink) {
        this.drink = drink;
    }

    @Override
    public float cost() {
        //getPrice自己的价格
        return super.getPrice() + drink.cost();
    }

    @Override
    public String getDescribe() {
        //drink.getDescribe()被装饰者的信息
        return super.describe + "  " + super.getPrice() + " && " + drink.getDescribe();
    }
}
