package com.example.ThreadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author ye
 * createDate 2022/3/24  9:17
 */
public class Demo01 {
    public static void main(String[] args) {
        //一池五线程
        ExecutorService threadPool01 = Executors.newFixedThreadPool(5);

        //一池一线程
        //ExecutorService threadPool02 = Executors.newWorkStealingPool();

        //一池可扩容
        ExecutorService threadPool03 = Executors.newCachedThreadPool();

        //十个请求
        try {
            for (int i = 0; i < 20; i++) {
                threadPool03.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "办理义务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool01.shutdown();
        }
    }
}
