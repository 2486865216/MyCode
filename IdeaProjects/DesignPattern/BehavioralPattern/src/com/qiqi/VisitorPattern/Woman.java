package com.qiqi.VisitorPattern;

/**
 * author ye
 * createDate 2022/2/7  14:44
 */
public class Woman extends Person{
    @Override
    public void accept(Action action) {
        action.getWomanResult(this);
    }
}
