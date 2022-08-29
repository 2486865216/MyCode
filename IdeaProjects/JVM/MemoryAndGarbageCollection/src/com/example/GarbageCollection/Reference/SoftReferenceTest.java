package com.example.GarbageCollection.Reference;


import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

/**
 * author ye
 * createDate 2022/2/16  12:18
 * -Xms10m -Xmx10m -XX:+PrintGCDetails
 */
public class SoftReferenceTest {
    public static void main(String[] args) {
        Reference<Object> ref = new SoftReference<>(new User(1,"Hello Java"));
        System.out.println(ref.get());
        System.gc();
        System.out.println("After GC");
        //获取强引用
        System.out.println(ref.get());
        try {
            byte[] buffer = new byte[1024 * 7168 - 1024 * 644];
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println();
            System.out.println(ref.get());
            System.out.println();
        }
    }
}
