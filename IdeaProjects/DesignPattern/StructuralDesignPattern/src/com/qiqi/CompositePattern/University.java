package com.qiqi.CompositePattern;

import java.util.ArrayList;
import java.util.List;

/**
 * author ye
 * createDate 2022/2/6  12:42
 */
public class University extends OrganizationComponent{

    //存放Collage,学院
    List<OrganizationComponent> organizationComponents = new ArrayList<>();

    public University(String name, String description) {
        super(name, description);
    }

    @Override
    protected void add(OrganizationComponent organizationComponent) {
        organizationComponents.add(organizationComponent);
    }

    @Override
    protected void remove(OrganizationComponent organizationComponent) {
        organizationComponents.add(organizationComponent);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    /**
     * 输出University包含的学院
     */
    @Override
    public void print() {
        System.out.println("=========="+getName()+"==========");
        for (OrganizationComponent organizationComponent : organizationComponents) {
            organizationComponent.print();
        }
    }
}
