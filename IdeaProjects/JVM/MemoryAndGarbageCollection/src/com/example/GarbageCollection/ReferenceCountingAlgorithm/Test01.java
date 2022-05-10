package com.example.GarbageCollection.ReferenceCountingAlgorithm;

/**
 * author ye
 * createDate 2022/2/15  13:23
 * -XX:+PrintGCDetails
 * java使用的不是引用计数算法
 */
public class Test01 {
    private byte[] bigSize = new byte[1024 * 1024 * 5];//5M
    Object reference = null;

    public static void main(String[] args) {
        Test01 obj1 = new Test01();
        Test01 obj2 = new Test01();

        obj1.reference = obj2;
        obj2.reference = obj1;

        obj1 = null;
        obj2 = null;

        System.gc();
    }
}
