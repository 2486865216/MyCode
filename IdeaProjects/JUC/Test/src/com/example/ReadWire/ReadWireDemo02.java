package com.example.ReadWire;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * author ye
 * createDate 2022/3/23  20:10
 * 锁降级
 */
public class ReadWireDemo02 {
    public static void main(String[] args) {
        //可重入读写锁对象
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();

        //锁降级
        writeLock.lock();
        System.out.println("Hello World");

        readLock.lock();
        System.out.println("----read");

        writeLock.unlock();
        readLock.unlock();
    }
}
