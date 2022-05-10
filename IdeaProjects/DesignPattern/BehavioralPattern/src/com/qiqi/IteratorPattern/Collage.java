package com.qiqi.IteratorPattern;

import java.util.Iterator;

/**
 * author ye
 * createDate 2022/2/8  11:31
 */
public interface Collage {
    public String getName();
    //增加系的方法
    public void addDepartment(String name, String description);
    //返回一个迭代器
    public Iterator createIterator();
}
