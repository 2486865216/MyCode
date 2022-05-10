package com.qiqi.StatePattern;

/**
 * author ye
 * createDate 2022/2/9  13:50
 * 状态抽象类
 */
public abstract class State {
    //扣除50积分
    public abstract void deducyMoney();
    //是否抽中
    public abstract boolean raffle();
    //发放奖品
    public abstract void dispensePrize();
}