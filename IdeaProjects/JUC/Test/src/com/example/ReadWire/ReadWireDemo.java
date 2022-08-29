package com.example.ReadWire;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * author ye
 * createDate 2022/3/23  19:49
 */
class MyCache{
    private volatile Map<String, Object> map = new HashMap<>();

    //创建读写锁
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    //放数据
    public void put(String key, Object value){
        //添加写锁
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "正在写操作");
            Thread.sleep(300);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写完了" + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //释放写锁
            lock.writeLock().unlock();
        }
    }

    //取数据
    public Object get(String key){

        //添加读锁
        lock.readLock().lock();
        Object result = null;
        try {
            System.out.println(Thread.currentThread().getName() + "正在读操作");
            Thread.sleep(300);
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "取完了" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.readLock().unlock();
        }
        return result;
    }
}
public class ReadWireDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.put(num + "", num + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.get(num + "");
            }, String.valueOf(i)).start();
        }
    }
}
