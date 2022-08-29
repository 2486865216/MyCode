package com.qiqi.CompositePattern;

import java.util.ArrayList;
import java.util.List;

/**
 * author ye
 * createDate 2022/2/6  12:49
 */
public class Department extends OrganizationComponent{
    public Department(String name, String description) {
        super(name, description);
    }
    //啊大大，remove不用写，因为是叶子节点

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public void print() {
        System.out.println("=========="+getName()+"==========");
    }
}
