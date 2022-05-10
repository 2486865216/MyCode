package com.example.Heap;

/**
 * author ye
 * createDate 2022/2/12  12:25
 * -Xms20m -Xmx20m
 * 打印GC
 *  -XX:+PrintGCDetails
 */
public class HeapDome01 {
    public static void main(String[] args) {
        System.out.println("start.....");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
