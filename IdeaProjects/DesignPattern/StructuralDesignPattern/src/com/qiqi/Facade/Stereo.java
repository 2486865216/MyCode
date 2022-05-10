package com.qiqi.Facade;

import sun.security.jca.GetInstance;

/**
 * author ye
 * createDate 2022/2/6  13:32
 */
public class Stereo {
    private static Stereo instance = new Stereo();
    public static Stereo GetInstance(){
        return instance;
    }
    public void on(){
        System.out.println("Stereo on");
    }
    public void off(){
        System.out.println("Stereo off");
    }
}
