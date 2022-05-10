package com.example.Native;

/**
 * author ye
 * createDate 2022/2/12  11:51
 */
public class NativeTest01 {
    public native void Native1(int x);

    public native static long Native2();

    private native synchronized float Native3(Object o);

    native void Native4(int[] any) throws Exception;
}
