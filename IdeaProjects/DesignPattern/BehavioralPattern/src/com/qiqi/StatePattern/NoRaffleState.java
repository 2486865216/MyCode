package com.qiqi.StatePattern;

/**
 * author ye
 * createDate 2022/2/9  13:58
 * 不能抽奖的状态
 */
public class NoRaffleState extends State{
    //初始化是传入活动，扣除积分后改变状态
    RaffleActivity activity;

    public NoRaffleState(RaffleActivity activity) {
        this.activity = activity;
    }

    //当前状态可以扣积分,扣除后将状态设置为抽奖
    @Override
    public void deducyMoney() {
        System.out.println("扣除了50积分,您可以抽奖了!");
        activity.setState(activity.getCanRaffleState());
    }

    //
    @Override
    public boolean raffle() {
        System.out.println("扣了积分才能抽奖哦！");
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("不能发放奖品");
    }
}
