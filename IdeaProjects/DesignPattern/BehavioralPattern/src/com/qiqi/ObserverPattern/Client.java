package com.qiqi.ObserverPattern;

/**
 * author ye
 * createDate 2022/2/8  12:41
 */
public class Client {
    public static void main(String[] args) {
        //创建一个weatherData
        WeatherData weatherData = new WeatherData();

        //创建观察者
        CurrentCondition currentCondition = new CurrentCondition();

        //注册到WeatherData
        weatherData.registerObserver(currentCondition);

        //测试
        System.out.println("通知各个观察者，信息更新");
        weatherData.setData(1.0f, 2.0f, 3.0f);
    }
}
