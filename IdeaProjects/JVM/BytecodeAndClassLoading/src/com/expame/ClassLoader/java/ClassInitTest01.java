package com.expame.ClassLoader.java;

/**
 * author ye
 * createDate 2022/2/10  13:34
 */
public class ClassInitTest01 {
    private static int num = 1;
    static {
        int num = 2;
        int number = 20;
    }
    private static int number = 10;
    public static void main(String[] args) {
        System.out.println(ClassInitTest01.num);
        System.out.println(ClassInitTest01.number);
    }
}
