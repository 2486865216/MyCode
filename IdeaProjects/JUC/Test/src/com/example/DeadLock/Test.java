package com.example.DeadLock;

/**
 * author ye
 * createDate 2022/3/23  13:05
 */
public class Test {
    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();
        new Thread(() -> {
            synchronized (o1){
                System.out.println(Thread.currentThread().getName() + "  持有o1,试图获取o2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2){
                    System.out.println(Thread.currentThread().getName() + "试图获取o2");
                }
            }
        },"A").start();

        new Thread(() -> {
            synchronized (o2){
                System.out.println(Thread.currentThread().getName() + "  持有o2,试图获取o1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1){
                    System.out.println(Thread.currentThread().getName() + "试图获取o1");
                }
            }
        },"B").start();
    }
}
