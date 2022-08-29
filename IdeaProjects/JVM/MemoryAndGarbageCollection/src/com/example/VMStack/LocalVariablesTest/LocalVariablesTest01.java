package com.example.VMStack.LocalVariablesTest;

/**
 * author ye
 * createDate 2022/2/11  14:25
 */
public class LocalVariablesTest01 {
    public static void main(String[] args) {
        LocalVariablesTest01 localVariablesTest01 = new LocalVariablesTest01();
        int n = 10;
        localVariablesTest01.test01();
    }
    public void test01(){
        String s = "hello java";
        System.out.println(s);
    }
    public void test02(){
        int a = 1;
        {
            int b = 0;
            b = a = 1;
        }
        //变量c使用之前已经被销毁的变量b的slot的位置
        int c = 2;

    }
}
