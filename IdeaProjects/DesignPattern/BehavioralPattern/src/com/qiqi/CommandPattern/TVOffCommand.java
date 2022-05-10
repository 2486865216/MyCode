package com.qiqi.CommandPattern;

/**
 * author ye
 * createDate 2022/2/7  14:14
 */
public class TVOffCommand implements Command {
    TVReceiver tvReceiver;

    public TVOffCommand(TVReceiver tvReceiver) {
        this.tvReceiver = tvReceiver;
    }

    @Override
    public void execute() {
        tvReceiver.off();
    }

    @Override
    public void undo() {
        tvReceiver.on();
    }
}
