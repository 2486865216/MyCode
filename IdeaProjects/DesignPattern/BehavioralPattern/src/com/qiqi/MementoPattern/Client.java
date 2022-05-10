package com.qiqi.MementoPattern;

/**
 * author ye
 * createDate 2022/2/8  13:58
 */
public class Client {
    public static void main(String[] args) {
        Originator originator = new Originator();
        CreateTaker createTaker = new CreateTaker();
        originator.setState("状态#1");
        //保存当前状态
        createTaker.addMemento(originator.saveStateMemento());

        originator.setState("状态#2");
        createTaker.addMemento(originator.saveStateMemento());

        //回到状态一
        System.out.println("当前的状态是:"+originator.getState());

        //恢复状态
        originator.getStateFromMemento(createTaker.get(0));
        System.out.println("恢复后的状态:"+originator.getState());
    }
}
