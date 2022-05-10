package com.qiqi.StatePattern;

import java.sql.Statement;

/**
 * author ye
 * createDate 2022/2/9  14:15
 */
public class RaffleActivity {
    //当前状态
    State state = null;
    //奖品数量
    int count = 0;
    //四个属性，表示四种状态
    State noRaffleState = new NoRaffleState(this);
    State canRaffleState = new CanRaffleState(this);
    State dispenseState = new DispenseState(this);
    State dispenseOutState = new DispenseOutState(this);

    public RaffleActivity(int count) {
        //初始化状态为不可抽奖
        this.state = noRaffleState;
        this.count = count;
    }
    //扣分
    public void debuctMoney(){
        state.deducyMoney();
    }
    //抽奖
    public void raffle(){
        //抽中
        if (state.raffle()){
            state.dispensePrize();
        }
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getCount() {
        int counter = this.count;
        this.count--;
        return counter;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public State getNoRaffleState() {
        return noRaffleState;
    }

    public void setNoRaffleState(State noRaffleState) {
        this.noRaffleState = noRaffleState;
    }

    public State getCanRaffleState() {
        return canRaffleState;
    }

    public void setCanRaffleState(State canRaffleState) {
        this.canRaffleState = canRaffleState;
    }

    public State getDispenseState() {
        return dispenseState;
    }

    public void setDispenseState(State dispenseState) {
        this.dispenseState = dispenseState;
    }

    public State getDispenseOutState() {
        return dispenseOutState;
    }

    public void setDispenseOutState(State dispenseOutState) {
        this.dispenseOutState = dispenseOutState;
    }
}
