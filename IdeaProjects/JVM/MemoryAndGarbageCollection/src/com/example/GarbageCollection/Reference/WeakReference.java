package com.example.GarbageCollection.Reference;

import java.lang.ref.Reference;

/**
 * author ye
 * createDate 2022/2/16  12:31
 */
public class WeakReference {
    public static void main(String[] args) {
        Reference<Object> ref= new java.lang.ref.WeakReference<>(new User(1,"Hello JVM"));
        System.out.println(ref.get());
        System.gc();
        System.out.println(ref.get());
    }
}
