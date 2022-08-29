package com.qiqi.CommandPattern;

/**
 * author ye
 * createDate 2022/2/7  13:44
 * 创建命令接口
 */
public interface Command {
    //执行操作
    public void execute();
    //撤销操作
    public void undo();
}
