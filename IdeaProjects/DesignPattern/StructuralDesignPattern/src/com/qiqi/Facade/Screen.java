package com.qiqi.Facade;

import java.util.Scanner;

/**
 * author ye
 * createDate 2022/2/6  13:31
 */
public class Screen {
    private static Screen instance = new Screen();
    public static Screen getInstance(){
        return instance;
    }
    public void up(){
        System.out.println("Screen up");
    }
    public void down(){
        System.out.println("Screen down");
    }
}
