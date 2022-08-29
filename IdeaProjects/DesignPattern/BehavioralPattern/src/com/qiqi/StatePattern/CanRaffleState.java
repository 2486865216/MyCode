package com.qiqi.StatePattern;

import java.util.Random;

/**
 * author ye
 * createDate 2022/2/9  14:03
 */
public class CanRaffleState extends State{
    RaffleActivity activity;

    public CanRaffleState(RaffleActivity activity) {
        this.activity = activity;
    }

    @Override
    public void deducyMoney() {
        System.out.println("已经扣除积分");
    }

    @Override
    public boolean raffle() {
        System.out.println("正在抽奖");
        Random random = new Random();
        int num = random.nextInt(10);
        //10%的机会
        if (num == 0){
            activity.setState(activity.getDispenseState());
            return true;
        }else {
            System.out.println("很遗憾没抽中!");
            activity.setState(activity.getNoRaffleState());
            return false;
        }
    }

    @Override
    public void dispensePrize() {
        System.out.println("没中奖，不能发放奖品");
    }
}
