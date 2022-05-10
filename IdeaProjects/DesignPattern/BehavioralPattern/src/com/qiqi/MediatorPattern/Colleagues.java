package com.qiqi.MediatorPattern;

/**
 * author ye
 * createDate 2022/2/8  13:08
 * 同时抽象类
 */
public abstract class Colleagues {
    private Mediator mediator;
    public String name;

    public Colleagues(Mediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }
    public Mediator getMediator(){
        return this.mediator;
    }

    public abstract void SendMessage();
}
