package com.qiqi.VisitorPattern;

/**
 * author ye
 * createDate 2022/2/7  14:42
 */
public class Success extends Action{
    @Override
    public void getManResult(Man man) {
        System.out.println("男人给的评价是成功");
    }

    @Override
    public void getWomanResult(Woman woman) {
        System.out.println("女人给的评价是成功");

    }
}
