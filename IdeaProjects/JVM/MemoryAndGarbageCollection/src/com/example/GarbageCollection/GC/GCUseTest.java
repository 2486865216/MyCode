package com.example.GarbageCollection.GC;

import java.util.ArrayList;
import java.util.List;

/**
 * author ye
 * createDate 2022/2/16  13:35
 * -XX:+PrintCommandLineFlags -XX:+UseSerialGC指定年轻代和老年代都使用串行收集器
 */
public class GCUseTest {
    public static void main(String[] args) throws InterruptedException {
        List<byte[]> list = new ArrayList<>();
        while (true){
            byte[] buffer = new byte[100];
            Thread.sleep(10);
        }
    }
}
