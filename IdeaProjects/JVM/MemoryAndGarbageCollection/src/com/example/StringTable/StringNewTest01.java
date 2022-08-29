package com.example.StringTable;

/**
 * author ye
 * createDate 2022/2/14  15:20
 * 两个
 *   一个是new关键字在堆空间创建
 *   一个是字符串常量池中的对象,字节码指令 ldc
 */
public class StringNewTest01 {
    public static void main(String[] args) {
        //s存储的是堆空间的引用
        String s = new String("ab");
    }
}
