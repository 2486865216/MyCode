package com.qiqi.TemplateMethodPattern;

/**
 * author ye
 * createDate 2022/2/7  11:56
 */
public class TestSoyaMilk extends SoyaMilk{
    @Override
    void add() {
        System.out.println("不加配料");
    }

    @Override
    boolean customWant() {
        return false;
    }
}
