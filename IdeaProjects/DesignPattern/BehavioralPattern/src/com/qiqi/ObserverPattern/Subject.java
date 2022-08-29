package com.qiqi.ObserverPattern;

/**
 * author ye
 * createDate 2022/2/8  12:23
 * 接口，让weatherData实现
 */
public interface Subject {
    public void registerObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObserver();
}
