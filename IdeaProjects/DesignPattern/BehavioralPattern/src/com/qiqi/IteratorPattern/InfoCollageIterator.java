package com.qiqi.IteratorPattern;

import java.util.Iterator;
import java.util.List;

/**
 * author ye
 * createDate 2022/2/8  11:28
 */
public class InfoCollageIterator implements Iterator {
    //以List集合的方式
    List<Department> departmentList;
    int index = -1;

    public InfoCollageIterator(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    @Override
    public boolean hasNext() {
        if (index >= departmentList.size() - 1){
            return false;
        }else{
            index += 1;
            return true;
        }
    }

    @Override
    public Object next() {
        return departmentList.get(index);
    }

    @Override
    public void remove() {

    }
}
