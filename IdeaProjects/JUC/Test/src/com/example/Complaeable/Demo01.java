package com.example.Complaeable;

import java.util.Currency;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * author ye
 * createDate 2022/3/24  10:12
 */
public class Demo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //异步调用，没有返回值
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "completableFuture");
        });
        completableFuture.get();


        //异步调用，有返回值
        CompletableFuture<Integer> completableFuture02 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "completableFuture02");
            return 1024;
        });
        completableFuture02.whenComplete((t, u) -> {
            System.out.println("t===" + t );//返回值
            System.out.println("u===" + u );//异常
        }).get();
    }
}
