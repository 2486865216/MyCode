package com.example.Heap;

import java.util.ArrayList;
import java.util.List;

/**
 * author ye
 * -Xms9m -Xmx9m -XX:+PrintGCDetails
 * createDate 2022/2/12  15:14
 * 测试Minor GC,Major GC, Full GC
 */
public class GCTest {
    public static void main(String[] args) {
        int i = 0;
        try {
            List<String> list = new ArrayList<>();
            String a = "atguigu.com";
            while (true){
                list.add(a);
                a = a + a;
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("遍历次数为: "+i);
        }
    }
}
