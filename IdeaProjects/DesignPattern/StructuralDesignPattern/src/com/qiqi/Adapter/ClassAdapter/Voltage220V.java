package com.qiqi.Adapter.ClassAdapter;

/**
 * author ye
 * createDate 2022/1/27  13:17
 */
//适配类
public class Voltage220V {
    //输出220电压
    public int ouput220V(){
        int src = 220;
        System.out.println(src+"V");
        return src;
    }
}
