package com.qiqi.MediatorPattern;

import java.util.HashMap;
import java.util.Map;

/**
 * author ye
 * createDate 2022/2/8  13:21
 * 具体的中介者类
 */
public class CreateMediator extends Mediator{
    //同时对象集合
    private Map<String, Colleagues> colleaguesMap;
    private Map<String, String> interMap;

    public CreateMediator() {
        colleaguesMap = new HashMap<>();
        interMap = new HashMap<>();
    }

    @Override
    public void Register(String colleagueName, Colleagues colleagues) {
        colleaguesMap.put(colleagueName, colleagues);
        if (colleagues instanceof Tom){
            interMap.put("Tom", colleagueName);
        }else if (colleagues instanceof Jerry){
            interMap.put("Jerry", colleagueName);
        }
    }

    //中介者的核心方法,根据得到的消息，完成对应的任务
    //协调各个具体对象，完成任务
    @Override
    public void GetMessage(String colleagueName) {
        if (colleaguesMap.get(colleagueName) instanceof Tom){
            System.out.println("I am Tom");
        }else if (colleaguesMap.get(colleagueName) instanceof Jerry){
            System.out.println("I am Jerry");
        }
    }

    @Override
    public void SendMessage() {

    }
}
