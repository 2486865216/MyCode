package com.example.syncLockDemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author ye
 * createDate 2022/3/23  12:29
 * 可重入锁
 */
public class Test {
    public synchronized void add(){
        add();
    }
    public static void main(String[] args) {
        //synchronized
        //new Test().add();
        /*Object o = new Object();
        new Thread(() -> {
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + "::外层");

                synchronized (o){
                    System.out.println(Thread.currentThread().getName() + "::中层");

                    synchronized (o){
                        System.out.println(Thread.currentThread().getName() + "::内层");
                    }
                }
            }
        },"t1").start();*/

        //lock
        Lock lock = new ReentrantLock();
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "::外层");
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + "::内层");
                }finally {
                    lock.unlock();
                }
            }finally {
                lock.unlock();
            }
        },"t2").start();
    }
}
