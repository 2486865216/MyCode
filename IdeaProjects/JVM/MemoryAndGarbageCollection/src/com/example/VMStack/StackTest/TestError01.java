package com.example.VMStack.StackTest;

/**
 * author ye
 * createDate 2022/2/11  13:47
 *
 * 默认值 count:11418
 * 设置栈的大小 -Xss256k
 * 2458
 */
public class TestError01 {
    private static int Count = 1;
    public static void main(String[] args) {
        System.out.println(Count);
        Count++;
        main(args);//Exception in thread "main" java.lang.StackOverflowError
    }
}
