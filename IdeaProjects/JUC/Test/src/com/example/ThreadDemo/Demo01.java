package com.example.ThreadDemo;

import org.junit.Test;

/**
 * author ye
 * createDate 2022/3/21  20:26
 */
class TestThread {
    private Integer number = 0;

    public synchronized void increment() throws InterruptedException {
        //wait()那里睡，那里醒，会直接执行之后的代码，导致虚假唤醒
        //用while解决
        while (number != 0) {
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "::" + number);
        notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while (number != 1) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "::" + number);
        this.notifyAll();
    }
}

public class Demo01 {
    public static void main(String[] args) {
        TestThread testThread = new TestThread();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    testThread.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    testThread.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    testThread.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "CC").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    testThread.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "DD").start();
    }
}
