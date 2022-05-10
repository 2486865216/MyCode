package com.qiqi.ObserverPattern;

/**
 * author ye
 * createDate 2022/2/8  12:25
 * 观察者接口，由观察者实现
 */
public interface Observer {
    public void update(float temperature, float pressure, float humidity);
}
