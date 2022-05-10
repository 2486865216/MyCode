package com.qiqi.ObserverPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * author ye
 * createDate 2022/2/8  12:31
 * 包含最新的天气情况信息
 * 含有观察者集合,使用 ArrayList管理
 * 当数据有更新时,就主动的调用 ArrayList,通知所有的(接入方)就看到最新的信息
 */
public class WeatherData implements Subject{
    private float temperature;
    private float pressure;
    private float humidity;
    //观察者集合
    List<Observer> observers;

    public WeatherData() {
        observers = new ArrayList<>();
    }

    public void dataChange(){
        notifyObserver();
    }
    public void setData(float temperature, float pressure, float humidity){
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        dataChange();
    }
    //添加一个观察者
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }
    //移除一个观察者
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    //遍历所有观察者，再通知
    @Override
    public void notifyObserver() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update(temperature, pressure, humidity);
        }
    }
}
