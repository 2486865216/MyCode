package com.qiqi.StatePattern;

/**
 * author ye
 * createDate 2022/2/9  14:13
 * 奖品发放完毕
 */
public class DispenseOutState extends State{
    RaffleActivity activity;

    public DispenseOutState(RaffleActivity activity) {
        this.activity = activity;
    }

    @Override
    public void deducyMoney() {
        System.out.println("奖品发完了");
    }

    @Override
    public boolean raffle() {
        System.out.println("奖品发完了");
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("奖品发完了");
    }
}
