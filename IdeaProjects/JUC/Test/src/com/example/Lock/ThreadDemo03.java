package com.example.Lock;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author ye
 * createDate 2022/3/23  8:30
 */
class ShareResource{
    //定义标志位
    private int flag = 1; //1 AA 2 BB 3 CC

    private Lock lock = new ReentrantLock();

    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    //打印一次
    public void printOne(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 1){
                c1.await();
            }
            System.out.println(Thread.currentThread().getName() + "::第" + loop + "轮");
            flag = 2;
            //通知BB线程
            c2.signal();
        }finally{
            lock.unlock();
        }
    }
    //打印两次
    public void printTwo(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 2){
                c2.await();
            }
            for (int i = 0; i < 2; i++) {
                System.out.println(Thread.currentThread().getName() + "::第" + loop + "轮");
            }
            flag = 3;
            //通知CC线程
            c3.signal();
        }finally{
            lock.unlock();
        }
    }
    //打印三次
    public void printThree(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 3){
                c3.await();
            }
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + "::第" + loop + "轮");
            }
            flag = 1;
            //通知AA线程
            c1.signal();
        }finally{
            lock.unlock();
        }
    }
}
public class ThreadDemo03 {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    shareResource.printOne(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    shareResource.printTwo(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    shareResource.printThree(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();
    }
}
