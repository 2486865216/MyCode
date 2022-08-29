package com.qiqi.VisitorPattern;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * author ye
 * createDate 2022/2/7  14:50
 */
//数据管理，管理很多人
public class ObjectStructure {
    //维护一盒集合
    private List<Person> persons = new LinkedList<>();

    //增加到list
    public void attach(Person person){
        persons.add(person);
    }
    //移除
    public void detach(Person person){
        persons.remove(person);
    }

    //显示测评情况
    public void display(Action action){
        for (Person person : persons) {
            person.accept(action);
        }
    }
}
