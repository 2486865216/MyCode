package com.qiqi.Bridge;

/**
 * author ye
 * createDate 2022/2/6  10:54
 */
public class UPRightPhone extends Phone{
    public UPRightPhone(Brand brand) {
        super(brand);
    }

    @Override
    protected void open() {
        super.open();
        System.out.println("直立手机");
    }

    @Override
    protected void close() {
        super.close();
        System.out.println("直立手机");
    }

    @Override
    protected void call() {
        super.call();
        System.out.println("直立手机");
    }
}
