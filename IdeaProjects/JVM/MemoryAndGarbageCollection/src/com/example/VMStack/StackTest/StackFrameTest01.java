package com.example.VMStack.StackTest;

/**
 * author ye
 * createDate 2022/2/11  14:02
 */
public class StackFrameTest01 {
    public static void main(String[] args) {
        method1();
    }
    private static void method1(){
        System.out.println("method1执行");
        method2();
        System.out.println("method1结束");
    }

    private static void method2() {
        System.out.println("method2执行");
        System.out.println("method2结束");
    }
}
