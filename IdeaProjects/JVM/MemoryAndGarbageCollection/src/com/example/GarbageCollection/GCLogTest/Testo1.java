package com.example.GarbageCollection.GCLogTest;

import java.util.ArrayList;
import java.util.List;

/**
 * author ye
 * createDate 2022/2/17  13:53
 */
public class Testo1 {
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            byte[] buffer = new byte[1024 * 100];
            list.add(buffer);
        }
    }
}
