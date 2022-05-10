package com.example.Controller;

import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * author ye
 * createDate 2022/3/17  13:52
 */
public class OrderQueue {
    public static Queue<DeferredResult<Object>> deferredResultQueue = new ConcurrentLinkedDeque<>();
    public static void save(DeferredResult<Object> objectDeferredResult){
        deferredResultQueue.offer(objectDeferredResult);
    }

    public static DeferredResult<Object> get(){
        return deferredResultQueue.poll();
    }
}
