package com.qiqi.IteratorPattern;

import java.util.Iterator;

/**
 * author ye
 * createDate 2022/2/8  11:33
 */
public class ComputerCollage implements Collage{
    Department[] departments;
    int numberOfDepartment = 0;//当前数组的对象个数

    public ComputerCollage() {
        departments = new Department[5];
        addDepartment("java","good");
        addDepartment("python","good");
        addDepartment("c++","good");
    }

    @Override
    public String getName() {
        return "计算机学院";
    }

    @Override
    public void addDepartment(String name, String description) {
        Department department = new Department(name, description);
        departments[numberOfDepartment] = department;
        numberOfDepartment += 1;
    }

    @Override
    public Iterator createIterator() {
        return new ComputerCollageIterator(departments);
    }
}
