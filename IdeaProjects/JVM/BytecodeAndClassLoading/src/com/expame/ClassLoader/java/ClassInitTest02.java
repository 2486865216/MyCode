package com.expame.ClassLoader.java;

/**
 * author ye
 * createDate 2022/2/10  13:42
 */
public class ClassInitTest02 {

    private static int a = 1;
    /*初始化阶段就是执行类构造器方法<clinit>()的过程。
      此方法不需定义,是 javac编译器自动收集类中的所有类变量的赋值动作和静态代码块中的语句合并而来。
     */
    public static void main(String[] args) {
        int b = 2;
    }
}
