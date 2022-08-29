package com.qiqi.CompositePattern;

import java.util.ArrayList;
import java.util.List;

/**
 * author ye
 * createDate 2022/2/6  12:47
 */
public class Collage extends OrganizationComponent{
    //存放Department,系
    List<OrganizationComponent> organizationComponents = new ArrayList<>();
    public Collage(String name, String description) {
        super(name, description);
    }

    @Override
    protected void add(OrganizationComponent organizationComponent) {
        organizationComponents.add(organizationComponent);
    }

    @Override
    protected void remove(OrganizationComponent organizationComponent) {
        organizationComponents.remove(organizationComponent);
    }

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
        for (OrganizationComponent organizationComponent : organizationComponents) {
            organizationComponent.print();
        }
    }
}
