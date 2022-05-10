package com.qiqi.CommandPattern;

/**
 * author ye
 * createDate 2022/2/7  13:51
 * 没有任何命令，即空执行，用于初始化
 * 省掉对空判断
 */
public class NoCommand implements Command{
    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
