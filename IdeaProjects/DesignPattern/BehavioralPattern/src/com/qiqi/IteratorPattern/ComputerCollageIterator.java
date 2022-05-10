package com.qiqi.IteratorPattern;

import java.util.Iterator;

/**
 * author ye
 * createDate 2022/2/8  11:23
 */
public class ComputerCollageIterator implements Iterator {
    //说明Department是以数组的形式储存
    Department[] departments;
    //遍历的起始位置
    int position = 0;

    public ComputerCollageIterator(Department[] departments) {
        this.departments = departments;
    }

    @Override
    public boolean hasNext() {
        if (position >= departments.length || departments[position]==(null)){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public Object next() {
        Department department = departments[position];
        position += 1;
        return department;
    }

    @Override
    public void remove() {
    }
}
