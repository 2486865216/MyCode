package com.yuye.DependenceInversionPrinciple;

/**
 * author ye
 * createDate 2022/1/25  13:19
 */
public class DependenceInversionPrinciple02 {
    public static void main(String[] args){
        Person1 person1 = new Person1();
        person1.receive(new Email1());
        person1.receive(new Wechat1());
    }
}
interface IReceiver{
    public String getInfo();
}
class Email1 implements IReceiver{
    @Override
    public String getInfo(){
        return "Hello Email";
    }
}
class Wechat1 implements IReceiver{
    @Override
    public String getInfo(){
        return "Hello Wechat";
    }
}
class Person1{
    void receive(IReceiver iReceiver){
        System.out.println(iReceiver.getInfo());
    }
}
