package com.qiqi.Adapter.ObjectAdapter;

/**
 * author ye
 * createDate 2022/1/27  13:20
 */
public class VoltageAdapter extends Voltage220V implements Voltage5V {
    private Voltage220V voltage220V;

    //通过构造器传入实例
    public VoltageAdapter(Voltage220V voltage220V) {
        this.voltage220V = voltage220V;
    }

    @Override
    public int ouput5V() {
        int dstV = 0;
        if (voltage220V != null){
            int srcV = voltage220V.ouput220V();
            System.out.println("使用对象适配器适配");
            dstV = srcV / 44;
        }
        return dstV;
    }
}
