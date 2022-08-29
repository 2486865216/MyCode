package com.example.GarbageCollection.SystemGC;

/**
 * author ye
 * createDate 2022/2/16  11:21
 */
public class LocalVarGC {
    public void methods1(){
        byte[] buffer = new byte[1024 * 1024 * 10];
        System.gc();
    }
    public void methods2(){
        byte[] buffer = new byte[1024 * 1024 * 10];
        buffer = null;
        System.gc();
    }
    public void methods3(){
        {
            byte[] buffer = new byte[1024 * 1024 * 10];
        }
        System.gc();
    }
    public void methods4(){
        {
            byte[] buffer = new byte[1024 * 1024 * 10];
        }
        int value = 10;
        System.gc();
    }
    public void methods5(){
        methods1();
        System.gc();
    }
    public static void main(String[] args) {
        LocalVarGC localVarGC = new LocalVarGC();
        localVarGC.methods5();
    }
}
