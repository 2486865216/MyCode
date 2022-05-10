package com.example.StringTable;

/**
 * author ye
 * createDate 2022/2/14  15:22
 * 对象一：StringBuilder
 * 对象二：new String("a")
 * 对象三：字符串常量池中 "a"
 * 对象四：new String("b")
 * 对象五：字符串常量池中 "b"
 *
 * 深入
 *  StringBuilder的toString
 *      对象六：new String("ab")
 *      调用toString，字符串常量池中没有
 */
public class StringNewTest02 {
    public static void main(String[] args) {
        String s = new String("a") + new String("b");
    }
}
