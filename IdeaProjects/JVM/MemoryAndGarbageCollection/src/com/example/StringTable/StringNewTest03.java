package com.example.StringTable;

/**
 * author ye
 * createDate 2022/2/14  15:30
 */
public class StringNewTest03 {
    public static void main(String[] args) {
        //s1存储的是堆空间的引用,字符串常量池中也有
        String s1 = new String("1");
        ////s2存储的是字符串常量池的引用
        String s2 = "1";
        System.out.println(s1 == s2);//false

        //s3激素的是new String("11")
        String s3 = new String("1") + new String("1");
        //调用toString()字符串常量池中没有
        //字符串常量池中不存在"11"
        s3.intern();
        //常量池中没有创建"11",只是创建了一个指向堆空间的引用
        String s4 = "11";
        System.out.println(s3 == s4);//true
    }
}
