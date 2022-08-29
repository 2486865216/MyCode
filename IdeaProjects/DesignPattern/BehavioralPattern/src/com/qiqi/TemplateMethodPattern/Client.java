package com.qiqi.TemplateMethodPattern;

/**
 * author ye
 * createDate 2022/2/7  11:49
 */
public class Client {
    public static void main(String[] args) {
        //红豆豆浆
        System.out.println("红豆豆浆");
        SoyaMilk redBean = new RedBeanSoyaMilk();
        redBean.make();
        System.out.println("======================");

        //花生豆浆
        System.out.println("花生豆浆");
        SoyaMilk peanut = new PeanutSoyaMilk();
        peanut.make();
        System.out.println("======================");

        //原味豆浆
        System.out.println("原味豆浆");
        SoyaMilk yuanwei = new TestSoyaMilk();
        yuanwei.make();
        System.out.println("======================");
    }
}
