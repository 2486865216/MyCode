package com.qiqi.Adapter.ClassAdapter;

/**
 * author ye
 * createDate 2022/1/27  13:21
 */
public class Phone {
    //充电
    public void charging(Voltage5V voltage5V){
        if (voltage5V.ouput5V() == 5){
            System.out.println("电压为5V,可以充电");
        }else if (voltage5V.ouput5V() > 5){
            System.out.println("电压大于5V,不可以充电");
        }
    }
}
