package com.qiqi.TemplateMethodPattern;

/**
 * author ye
 * createDate 2022/2/7  11:56
 */
public class TestSoyaMilk extends SoyaMilk{
    @Override
    void add() {
        System.out.println("δΈε ιζ");
    }

    @Override
    boolean customWant() {
        return false;
    }
}
