package com.example.case02;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * author ye
 * createDate 2022/5/24  14:34
 */
public class CuratorLockTest {
    public static void main(String[] args) {
        //创建分布式锁一
        InterProcessMutex lock1 = new InterProcessMutex(getCuratorFramework(), "/locks");

        //创建分布式锁二
        InterProcessMutex lock2 = new InterProcessMutex(getCuratorFramework(), "/locks");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock1.acquire();
                    System.out.println("线程一获取到锁");

                    lock1.acquire();
                    System.out.println("线程一再次获取到锁");

                    Thread.sleep(5000);

                    lock1.release();
                    System.out.println("线程一释放锁");
                    lock1.release();
                    System.out.println("线程一再次释放锁");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock2.acquire();
                    System.out.println("线程二获取到锁");

                    lock2.acquire();
                    System.out.println("线程二再次获取到锁");

                    Thread.sleep(5000);

                    lock2.release();
                    System.out.println("线程二释放锁");
                    lock2.release();
                    System.out.println("线程二再次释放锁");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static CuratorFramework getCuratorFramework() {
        CuratorFramework build = CuratorFrameworkFactory.builder()
                .connectString("192.168.229.141:2181,192.168.229.142:2181,192.168.229.143:2181")
                .connectionTimeoutMs(200000)
                .sessionTimeoutMs(200000)
                .retryPolicy(new ExponentialBackoffRetry(3000, 3))
                .build();

        build.start();
        System.out.println("zookeeper 启动成功");
        return build;
    }
}
