package com.qiqi.StatePattern;

/**
 * author ye
 * createDate 2022/2/9  14:09
 * 奖品发放
 */
public class DispenseState extends State{
    RaffleActivity activity;

    public DispenseState(RaffleActivity activity) {
        this.activity = activity;
    }

    @Override
    public void deducyMoney() {
        System.out.println("不能扣除积分");
    }

    @Override
    public boolean raffle() {
        System.out.println("不能抽奖");
        return false;
    }

    @Override
    public void dispensePrize() {
        if (activity.getCount() > 0){
            System.out.println("恭喜中奖");
            //改变状态为不可抽奖
            activity.setState(activity.getNoRaffleState());
            if (activity.getCount() == 0){
                activity.setState(activity.dispenseOutState);
            }
        }else {
            System.out.println("很遗憾，奖品抽完了");
            activity.setState(activity.getDispenseOutState());
        }
    }
}
