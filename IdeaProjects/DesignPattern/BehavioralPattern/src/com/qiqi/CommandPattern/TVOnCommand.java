package com.qiqi.CommandPattern;

/**
 * author ye
 * createDate 2022/2/7  14:11
 */
public class TVOnCommand implements Command {
    TVReceiver tvReceiver;

    public TVOnCommand(TVReceiver tvReceiver) {
        this.tvReceiver = tvReceiver;
    }

    @Override
    public void execute() {
        tvReceiver.on();
    }

    @Override
    public void undo() {
        tvReceiver.off();
    }
}
