package com.yuye.Prototype.DeepClone;

import org.junit.Test;

/**
 * author ye
 * createDate 2022/1/27  10:27
 */
public class Client {
    @Test
    public void test() throws CloneNotSupportedException {
        DeepPrototype deepPrototype = new DeepPrototype();
        deepPrototype.name = "hello";
        deepPrototype.deepCloneableTest = new DeepCloneableTest("java", 18);
        //方式一
        DeepPrototype deepPrototype2 = (DeepPrototype) deepPrototype.clone();
        System.out.println("p1.name  "+deepPrototype.name+
                "  p1.deepCloneableTest  "+deepPrototype.deepCloneableTest.hashCode());

        System.out.println("p2.name  "+deepPrototype2.name+
                "  p2.deepCloneableTest  "+deepPrototype2.deepCloneableTest.hashCode());

        DeepPrototype deepPrototype3 = new DeepPrototype();
        deepPrototype3.name = "Serializable";
        deepPrototype3.deepCloneableTest = new DeepCloneableTest("design", 20);
        DeepPrototype deepPrototype4 = (DeepPrototype) deepPrototype3.deepClone();

        System.out.println("p3.name  "+deepPrototype3.name+
                "  p3.deepCloneableTest  "+deepPrototype3.deepCloneableTest.hashCode());

        System.out.println("p4.name  "+deepPrototype4.name+
                "  p4.deepCloneableTest  "+deepPrototype4.deepCloneableTest.hashCode());
    }
}
