package com.example.Lock;


import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author ye
 * createDate 2022/3/23  8:14
 */
class Share{
    private int number = 0;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        try {
            while (number != 0){
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "::" + number);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (number != 1){
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "::" + number);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

}
public class ThreadDemo02 {
    public static void main(String[] args) {
        Share share = new Share();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    share.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    share.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BB").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    share.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"CC").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    share.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"DD").start();
    }
}
