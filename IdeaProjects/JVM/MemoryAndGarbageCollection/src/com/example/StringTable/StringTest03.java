package com.example.StringTable;

import org.junit.Test;

/**
 * author ye
 * createDate 2022/2/14  14:39
 * 字符串拼接
 */
public class StringTest03 {
    @Test
    public void test() {
        //编译器优化
        String s1 = "a" + "b" + "c";
        String s2 = "abc";
        System.out.println(s1 == s2);//true
        System.out.println(s1.equals(s2));//true
    }

    @Test
    public void test2() {
        String s1 = "javaEE";
        String s2 = "Hadoop";

        //编译器优化
        String s3 = "javaEEHadoop";
        String s4 = "javaEE" + "Hadoop";
        //只要其中有一个是变量,结果就在堆中,相当于在堆中new String()
        String s5 = s1 + "Hadoop";
        String s6 = "javaEE" + s2;
        String s7 = s1 + s2;

        System.out.println(s3 == s4);//true
        System.out.println(s3 == s5);//false
        System.out.println(s3 == s6);//false
        System.out.println(s3 == s7);//false
        System.out.println(s5 == s6);//false
        System.out.println(s5 == s7);//false
        System.out.println(s6 == s7);//false

        //intern()判断字符串常量池中是否存在“javaEEHadoop”，如果存在则返回常量池中javaEEHadoop的地址
        //不存在，则在常量池中加载一份javaEEHadoop，并返回此对象的地址
        String s8 = s6.intern();
        System.out.println(s3 == s8);//true
    }

    @Test
    public void test3() {
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        String s4 = s1 + s2;
        //细节
        //StringBuilder s = new StringBuilder();
        //s.append(s1)
        //s.append(s2)
        //s.toString() -->约等于new String("ab")
        System.out.println(s3 == s4);//false
    }

    @Test
    public void test4() {
        //拼接两边都是常量，则仍使用编译器优化
        final String s1 = "a";
        final String s2 = "b";
        String s3 = "ab";
        String s4 = s1 + s2;
        System.out.println(s3 == s4);//true
    }
    //体会执行效率:通过 StringBuilder的 append()的方式添加字符的效率要远高于使用 string的字符申拼接方式!
    @Test
    public void test5() {
        //methods1(100000);//花费的时间:7126
        methods2(100000);//花费的时间:7
    }
    public void methods1(int count){
        String s = "";
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            s = s + "a";//每次都new一个String
        }
        System.out.println("花费的时间:"+(System.currentTimeMillis() - start));
    }
    public void methods2(int count){
        //只需要new一个StringBuilder
        StringBuilder s = new StringBuilder();
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            s.append("a");
        }
        System.out.println("花费的时间:"+(System.currentTimeMillis() - start));
    }
}
