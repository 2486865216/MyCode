package com.qiqi.MediatorPattern;

import java.util.Collection;

/**
 * author ye
 * createDate 2022/2/8  13:31
 */
public class Client {
    public static void main(String[] args) {
        //创建一个中介者
        Mediator mediator = new CreateMediator();
        //创建一个Tom
        Tom tom = new Tom(mediator, "tom");
        Jerry jerry = new Jerry(mediator, "jerry");

        tom.SendMessage();
        jerry.SendMessage();
    }
}
