package com.example.VMStack.Invoke;

/**
 * author ye
 * createDate 2022/2/11  16:22
 */
public class testFunction {
    public void test(Func func) {
        func.test("hello");
    }
    public static void main(String[] args) {
        Func func = s -> {
            System.out.println(s);
        };
        func.test("hello");
        testFunction function = new testFunction();
        function.test(func);
    }
}
