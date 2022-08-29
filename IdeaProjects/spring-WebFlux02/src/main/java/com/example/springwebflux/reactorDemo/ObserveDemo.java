package com.example.springwebflux.reactorDemo;

import java.util.Observable;

/**
 * author ye
 * createDate 2022/4/18  14:49
 */
public class ObserveDemo extends Observable {
    public static void main(String[] args) {
        ObserveDemo observeDemo = new ObserveDemo();
        //添加观察者
        observeDemo.addObserver((o, arg) -> {
            System.out.println("发生变化");
        });

        observeDemo.addObserver((o, arg) -> {
            System.out.println("准备改变");
        });

        //数据变化
        observeDemo.setChanged();
        //通知
        observeDemo.notifyObservers();
    }
}
