package com.yuye.Signleton;

/**
 * author ye
 * createDate 2022/1/26  12:13
 */
public class Signleton08 {
    public static void main(String[] args) {
        SignTonTest instance = SignTonTest.INSTANCE;
        SignTonTest instance1 = SignTonTest.INSTANCE;
        System.out.println(instance == instance1);
        instance.sayOK();
    }
}
enum SignTonTest{
    INSTANCE;
    public void sayOK(){
        System.out.println("ok");
    }
}
