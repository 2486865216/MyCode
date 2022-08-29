package com.example.StringTable;

import org.junit.Test;

/**
 * author ye
 * createDate 2022/2/14  14:07
 * String 不可变
 */
public class StringTest01 {
    @Test
    public void test() {
        String s1 = "abc";
        String s2 = "abc";
        System.out.println(s1 == s2);
        s2 = s2 + "def";
        System.out.println(s1 == s2);
    }
}
