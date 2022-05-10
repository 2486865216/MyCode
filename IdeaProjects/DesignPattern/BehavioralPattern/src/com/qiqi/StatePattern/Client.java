package com.qiqi.StatePattern;

/**
 * author ye
 * createDate 2022/2/9  14:22
 */
public class Client {
    public static void main(String[] args) {
        RaffleActivity activity = new RaffleActivity(1);
        for (int i = 0; i < 10; i++) {
            System.out.println("===第"+(i + 1)+"次抽奖===");
            activity.debuctMoney();
            activity.raffle();
        }
    }
}
