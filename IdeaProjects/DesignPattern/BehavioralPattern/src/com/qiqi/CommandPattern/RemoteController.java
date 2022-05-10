package com.qiqi.CommandPattern;

/**
 * author ye
 * createDate 2022/2/7  13:53
 */
public class RemoteController {
    //开 按钮命令数组
    Command[] onCommand;
    Command[] offCommand;

    //执行撤销的命令
    Command undoCommand;

    //构造器，初始化

    public RemoteController() {
        this.onCommand = new Command[5];
        this.offCommand = new Command[5];
        for (int i = 0; i < 5; i++) {
            onCommand[i] = new NoCommand();
            offCommand[i] = new NoCommand();
        }
    }
    //给按钮设置需要的命令
    public void setCommand(int on, Command onCommand, Command offCommand){
        this.onCommand[on] = onCommand;
        this.offCommand[on] = offCommand;
    }
    //按下开按钮
    public void onButtonWasPushed(int on){
        //调用对应的按钮方法
        onCommand[on].execute();
        //记录操作用于撤销
        undoCommand = onCommand[on];
    }
    //按下关按钮
    public void offButtonWasPushed(int on){
        //调用对应的按钮方法
        offCommand[on].execute();
        //记录操作用于撤销
        undoCommand = offCommand[on];
    }
    //撤销
    public void undoButtonWasPushed(){
        undoCommand.undo();
    }
}
