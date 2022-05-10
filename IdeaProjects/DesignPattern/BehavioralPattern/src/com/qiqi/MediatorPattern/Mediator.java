package com.qiqi.MediatorPattern;

/**
 * author ye
 * createDate 2022/2/8  13:05
 */
public abstract class Mediator {
    //将一个中介者对象加入到集合中
    public abstract void Register(String colleagueName, Colleagues colleagues);

    //接受信息具体的同时对象发出
    public abstract void GetMessage(String colleagueName);

    public abstract void SendMessage();
}
