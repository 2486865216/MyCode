package com.qiqi.Adapter.ClassAdapter;

/**
 * author ye
 * createDate 2022/1/27  13:20
 */
public class VoltageAdapter extends Voltage220V implements Voltage5V{
    @Override
    public int ouput5V() {
        int srcV = ouput220V();
        int dstV = srcV/44;
        return dstV;
    }
}
