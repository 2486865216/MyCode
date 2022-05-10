package com.qiqi.Facade;

/**
 * author ye
 * createDate 2022/2/6  13:29
 */
public class TV {
    private static TV instance = new TV();
    public static TV getInstance(){
        return instance;
    }
    public void on(){
        System.out.println("TV on");
    }
    public void off(){
        System.out.println("TV off");
    }
    public void play(){
        System.out.println("TV play");
    }
}
