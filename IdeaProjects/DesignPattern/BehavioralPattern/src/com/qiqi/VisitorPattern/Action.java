package com.qiqi.VisitorPattern;

/**
 * author ye
 * createDate 2022/2/7  14:40
 */
public abstract class Action {
    //男性的评价
    public abstract void getManResult(Man man);
    //女性的评价
    public abstract void getWomanResult(Woman woman);
}
