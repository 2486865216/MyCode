package com.qiqi.Decorator;

/**
 * author ye
 * createDate 2022/2/6  11:36
 */
public abstract class Drink {
    protected String describe;//描述
    private float price = 0.0f;

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    //计算费用的抽象的方法
    //子类实现
    public abstract float cost();
}
