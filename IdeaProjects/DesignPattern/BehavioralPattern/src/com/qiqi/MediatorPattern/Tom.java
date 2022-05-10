package com.qiqi.MediatorPattern;

/**
 * author ye
 * createDate 2022/2/8  13:14
 */
public class Tom extends Colleagues{

    public Tom(Mediator mediator, String name) {
        super(mediator, name);
        mediator.Register(name, this);
    }
    @Override
    public void SendMessage() {
        this.getMediator().GetMessage(this.name);
    }
}
