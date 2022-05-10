package com.example.GarbageCollection.SystemGC;

/**
 * author ye
 * createDate 2022/2/16  11:11
 */
public class SystemGCTest01 {
    public static void main(String[] args) {
        new SystemGCTest01();
        System.gc();//提醒JVM的垃圾回收器执行gc,但是不确定是否马上执行gc
        System.runFinalization();//强制调用失去引用的对象的finalize()方法
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("重写了finalize");
    }
}
