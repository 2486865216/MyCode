package com.example.case02;

import org.apache.zookeeper.KeeperException;

/**
 * author ye
 * createDate 2022/5/24  14:24
 */
public class DistributedLockTest {
    public static void main(String[] args) throws InterruptedException, KeeperException {
        DistributedLock lock1 = new DistributedLock();
        DistributedLock lock2 = new DistributedLock();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock1.zkLock();
                    System.out.println("线程一获取倒锁");
                    Thread.sleep(5000);

                    lock1.zkUnLock();
                    System.out.println("线程一释放锁");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock2.zkLock();
                    System.out.println("线程二获取倒锁");
                    Thread.sleep(5000);

                    lock2.zkUnLock();
                    System.out.println("线程二释放锁");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
