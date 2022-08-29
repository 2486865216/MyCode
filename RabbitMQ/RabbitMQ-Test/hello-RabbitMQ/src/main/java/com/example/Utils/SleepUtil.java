package com.example.Utils;

/**
 * author ye
 * createDate 2022/2/24  20:30
 */
public class SleepUtil {
    public static void sleepTime(int second){
        try {
            Thread.sleep(1000 * second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
