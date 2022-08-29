package com.example.StringTable;

/**
 * author ye
 * createDate 2022/2/14  14:17
 * 测试String pool 大小
 * C:\Users\YQ>jps
 * 10192 StringTableSizeTest
 * 14832 Launcher
 * 2716 Jps
 * 5068
 *
 * C:\Users\YQ>jinfo -flag StringTableSize 10192
 * -XX:StringTableSize=60013
 */
public class StringTableSizeTest {
    public static void main(String[] args) {
        System.out.println("String Table pool");
        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
