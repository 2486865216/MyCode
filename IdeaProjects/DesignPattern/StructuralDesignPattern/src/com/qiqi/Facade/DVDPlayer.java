package com.qiqi.Facade;

import sun.security.jca.GetInstance;

/**
 * author ye
 * createDate 2022/2/6  13:26
 */
public class DVDPlayer {
    //使用单例,饿汉式
    private static DVDPlayer instance = new DVDPlayer();
    public static DVDPlayer GetInstance(){
        return instance;
    }
    public void on(){
        System.out.println("dvd on");
    }
    public void off(){
        System.out.println("dvd off");
    }
    public void play(){
        System.out.println("dvd play");
    }
}
