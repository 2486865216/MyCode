package com.qiqi.IteratorPattern;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * author ye
 * createDate 2022/2/8  11:38
 */
public class InfoCollage implements Collage{
    List<Department> departmentList;

    public InfoCollage() {
        departmentList = new ArrayList<>();
        addDepartment("信息安全","good");
        addDepartment("网络安全","good");
        addDepartment("数据安全","good");
    }

    @Override
    public String getName() {
        return "信息工程学院";
    }

    @Override
    public void addDepartment(String name, String description) {
        Department department = new Department(name, description);
        departmentList.add(department);
    }

    @Override
    public Iterator createIterator() {
        return new InfoCollageIterator(departmentList);
    }
}
