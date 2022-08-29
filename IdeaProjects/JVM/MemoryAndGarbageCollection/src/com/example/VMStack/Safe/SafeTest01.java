package com.example.VMStack.Safe;

/**
 * author ye
 * createDate 2022/2/12  11:27
 *
 * 方法中定义的局部变量是否线程安全?具体情况具体讨论
 */
public class SafeTest01 {
    //线程安全
    public void methods1(){
        StringBuilder s = new StringBuilder();
        s.append("a");
        s.append("b");
    }
    //线程不安全
    public static void methods2(StringBuilder s){
        s.append("a");
        s.append("b");
    }
    //线程不安全
    public static StringBuilder methods3(){
        StringBuilder s = new StringBuilder();
        s.append("a");
        s.append("b");
        return s;
    }
    //线程安全
    public static String methods4(){
        StringBuilder s = new StringBuilder();
        s.append("a");
        s.append("b");
        return s.toString();
    }
    public static void main(String[] args) {
        StringBuilder s = new StringBuilder();

        new Thread(() -> {
            s.append("a");
            s.append("b");
        });

        methods2(s);
    }
}
