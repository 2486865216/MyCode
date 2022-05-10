package com.qiqi.VisitorPattern;

import javax.swing.plaf.metal.OceanTheme;

/**
 * author ye
 * createDate 2022/2/7  14:54
 */
public class Client {
    public static void main(String[] args) {
        ObjectStructure objectStructure = new ObjectStructure();
        objectStructure.attach(new Man());
        objectStructure.attach(new Man());
        objectStructure.attach(new Man());

        //成功
        Success success = new Success();
        objectStructure.display(success);


        objectStructure.attach(new Woman());
        objectStructure.attach(new Woman());
        objectStructure.attach(new Woman());
        objectStructure.attach(new Woman());
        objectStructure.display(new Fail());
    }
}
