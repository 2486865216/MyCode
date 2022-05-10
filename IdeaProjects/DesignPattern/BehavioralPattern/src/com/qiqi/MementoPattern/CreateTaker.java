package com.qiqi.MementoPattern;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * author ye
 * createDate 2022/2/8  13:55
 */
public class CreateTaker {

    private List<Memento> mementoList = new ArrayList<>();

    public void addMemento(Memento memento){
        mementoList.add(memento);
    }
    //获取到第index个Originator的备忘录对象,(即保存状态)
    public Memento get(int index){
        return mementoList.get(index);
    }
}
