package com.qiqi.CommandPattern;

/**
 * author ye
 * createDate 2022/2/7  13:46
 */
public class LightOnCommand implements Command{
    LightReceiver lightReceiver;

    public LightOnCommand(LightReceiver lightReceiver) {
        this.lightReceiver = lightReceiver;
    }

    @Override
    public void execute() {
        //调用接受者的方法
        lightReceiver.on();
    }

    @Override
    public void undo() {
        lightReceiver.off();
    }
}
