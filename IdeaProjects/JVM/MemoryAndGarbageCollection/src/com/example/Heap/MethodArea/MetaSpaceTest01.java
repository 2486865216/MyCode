package com.example.Heap.MethodArea;

/**
 * author ye
 * createDate 2022/2/13  12:22
 * C:\Users\YQ>jps
 * 14720 Jps
 * 7540 Launcher
 * 2248
 * 10748 MetaSpaceTest01
 *
 * C:\Users\YQ>jinfo -flag MetaspaceSize 10748
 * -XX:MetaspaceSize=21807104
 *
 * C:\Users\YQ>
 */
public class MetaSpaceTest01 {
    public static void main(String[] args) {
        System.out.println("start...");
        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end...");
    }
}
