package com.qiqi.CompositePattern;

/**
 * author ye
 * createDate 2022/2/6  12:51
 */
public class Client {
    public static void main(String[] args) {
        //从大到小创建
        //学校
        OrganizationComponent university = new University("广西科技大学","666");
        //学院
        OrganizationComponent collage1 = new Collage("启迪数字学院","666");
        OrganizationComponent collage2 = new Collage("经济管理学院","222");
        //系
        collage1.add(new Department("软件工程","好"));
        collage1.add(new Department("网络安全","好"));

        collage2.add(new Department("会计","好"));

        //将学院加入到学校
        university.add(collage1);
        university.add(collage2);
        university.print();
    }
}
