package com.yuye.DependenceInversionPrinciple;

import java.time.Period;

/**
 * author ye
 * createDate 2022/1/25  13:15
 */
public class DependenceInversionPrinciple01 {
    public static void main(String[] args) {
        Person person = new Person();
        person.receive(new Email());
    }
}
class Email{
    public String getInfo(){
        return "Hello Word!";
    }
}
//如果接受微信等信息，就要新增类
class Person{
    public void receive(Email email){
        email.getInfo();
    }
}