package com.example.Heap;

/**
 * author ye
 * createDate 2022/2/13  11:31
 * -XX:+DoEscapeAnalysis
 */
public class StackAllocation {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            alloc();
        }
        System.out.println(System.currentTimeMillis() - start + "ms");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static void alloc(){
        User user = new User();
    }
}
class User{}
