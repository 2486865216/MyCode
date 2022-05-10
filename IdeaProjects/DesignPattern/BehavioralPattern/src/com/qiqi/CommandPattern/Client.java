package com.qiqi.CommandPattern;

/**
 * author ye
 * createDate 2022/2/7  14:03
 */
public class Client {
    public static void main(String[] args) {
        //命令模式，遥控器对电灯的操作
        //创建电灯对象（接收者）
        LightReceiver lightReceiver = new LightReceiver();

        //创建电灯的开关命令
        LightOnCommand lightOnCommand = new LightOnCommand(lightReceiver);
        LightOffCommand lightOffCommand = new LightOffCommand(lightReceiver);

        //遥控器
        RemoteController remoteController = new RemoteController();

        //给遥控器设置命令，on=0为电灯的开关操作
        remoteController.setCommand(0,lightOnCommand,lightOffCommand);

        System.out.println("--------按下开----------");
        remoteController.onButtonWasPushed(0);
        System.out.println("--------按下关----------");
        remoteController.offButtonWasPushed(0);
        System.out.println("--------按下撤销----------");
        remoteController.undoButtonWasPushed();

        System.out.println("操作电视");
        TVReceiver tvReceiver = new TVReceiver();
        TVOnCommand tvOnCommand = new TVOnCommand(tvReceiver);
        TVOffCommand tvOffCommand = new TVOffCommand(tvReceiver);
        remoteController.setCommand(1, tvOnCommand, tvOffCommand);
        System.out.println("--------按下开----------");
        remoteController.onButtonWasPushed(1);
        System.out.println("--------按下关----------");
        remoteController.offButtonWasPushed(1);
        System.out.println("--------按下撤销----------");
        remoteController.undoButtonWasPushed();
    }
}
