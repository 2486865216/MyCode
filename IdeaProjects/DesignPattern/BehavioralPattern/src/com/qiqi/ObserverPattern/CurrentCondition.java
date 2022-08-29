package com.qiqi.ObserverPattern;

/**
 * author ye
 * createDate 2022/2/8  12:26
 */
public class CurrentCondition implements Observer {
    //维度，气压，湿度
    private float temperature;
    private float pressure;
    private float humidity;


    @Override
    public void update(float temperature, float pressure, float humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        display();
    }
    //显示
    public void display(){
        System.out.println("Today temperature is:" +temperature);
        System.out.println("Today pressure is:" +pressure);
        System.out.println("Today humidity is:" +humidity);
    }
}
