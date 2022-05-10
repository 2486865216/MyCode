package com.example.GarbageCollection.Jprofier;

import java.util.ArrayList;
import java.util.List;

/**
 * author ye
 * createDate 2022/2/15  14:48
 * -Xms8m -Xmx8m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {
    private byte[] buffer = new byte[1024 * 1024];

    public static void main(String[] args) {
        List<HeapOOM> list = new ArrayList<>();
        int count = 0;
        try {
            while (true){
                list.add(new HeapOOM());
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(count);
    }
}
