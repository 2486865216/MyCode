package com.qiqi.Decorator;


/**
 * author ye
 * createDate 2022/2/6  11:54
 */
public class CoffeeBar {
    public static void main(String[] args) {
        //装饰者模式下的订单：2份巧克力+一份牛奶的LongBlack
        //点一份LongBlack
        Drink order = new LongBlack();
        System.out.println("费用一:"+order.cost());
        System.out.println("描述一："+order.getDescribe());

        //order加入一份牛奶
        order = new Milk(order);
        System.out.println("order加入一份牛奶之后的费用："+order.cost());
        System.out.println("order加入一份牛奶之后的描述："+order.getDescribe());

        //加入一份巧克力
        order = new Chocolate(order);
        System.out.println("order加入一份巧克力之后的费用："+order.cost());
        System.out.println("order加入一份巧克力之后的描述："+order.getDescribe());

        //加入一份巧克力
        order = new Chocolate(order);
        System.out.println("order加入二份巧克力之后的费用："+order.cost());
        System.out.println("order加入二份巧克力之后的描述："+order.getDescribe());
    }
}
