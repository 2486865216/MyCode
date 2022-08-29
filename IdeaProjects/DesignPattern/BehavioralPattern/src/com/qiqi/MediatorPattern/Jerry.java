package com.qiqi.MediatorPattern;

/**
 * author ye
 * createDate 2022/2/8  13:24
 */
public class Jerry extends Colleagues{
    public Jerry(Mediator mediator, String name) {
        super(mediator, name);
        mediator.Register(name, this);
    }
    @Override
    public void SendMessage() {
        this.getMediator().GetMessage(this.name);
    }
}
