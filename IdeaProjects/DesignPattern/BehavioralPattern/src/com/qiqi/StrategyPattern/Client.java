package com.qiqi.StrategyPattern;

/**
 * author ye
 * createDate 2022/2/9  15:07
 */
public class Client {
    public static void main(String[] args) {
        PekigDuck pekigDuck = new PekigDuck();
        pekigDuck.display();

        ToyDuck toyDuck = new ToyDuck();
        toyDuck.display();
    }
}
