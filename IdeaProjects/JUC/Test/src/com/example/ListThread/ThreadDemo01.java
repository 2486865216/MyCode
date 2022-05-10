package com.example.ListThread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * author ye
 * createDate 2022/3/23  9:00
 */
public class ThreadDemo01 {
    public static void main(String[] args) {
        //List<String> list = new ArrayList<>();

        //Vector解决
        //List<String> list = new Vector<>();

        //Collections解决
        //List<String> list = Collections.synchronizedList(new ArrayList<>());

        //CopyOnWriteArrayList解决
        //List<String> list = new CopyOnWriteArrayList<>();
        //for (int i = 0; i < 30; i++) {
        //    new Thread(() -> {
        //        list.add(UUID.randomUUID().toString().substring(0, 8));
        //        System.out.println(list);
        //    }, String.valueOf(i)).start();
        //}

        //Set<String> set  = new HashSet<>();
        //Set<String> set  = new CopyOnWriteArraySet<>();
        //for (int i = 0; i < 30; i++) {
        //    new Thread(() -> {
        //        set.add(UUID.randomUUID().toString().substring(0, 8));
        //        System.out.println(set);
        //    }, String.valueOf(i)).start();
        //}

        //Map<String, String> map = new HashMap<>();
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            String key = String.valueOf(i);
            new Thread(() -> {
                map.put(key, UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
