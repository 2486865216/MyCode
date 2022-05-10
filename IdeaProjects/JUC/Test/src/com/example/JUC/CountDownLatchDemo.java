package com.example.JUC;

import java.util.concurrent.CountDownLatch;

/**
 * author ye
 * createDate 2022/3/23  13:58
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //创建CountDownLatch对象’设置初始值
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "号同学离开了教室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        //等待
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "班长锁门走人");
    }
}
