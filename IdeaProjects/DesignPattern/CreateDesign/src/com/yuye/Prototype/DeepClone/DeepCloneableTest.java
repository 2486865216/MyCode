package com.yuye.Prototype.DeepClone;

import java.io.Serializable;

/**
 * author ye
 * createDate 2022/1/27  10:16
 */
public class DeepCloneableTest implements Serializable,Cloneable {
    private static final long serialVersionUID = -1L;
    public String name;
    public Integer age;

    public DeepCloneableTest() {
    }

    public DeepCloneableTest(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        DeepCloneableTest deepCloneableTest = null;
        try {
            deepCloneableTest = (DeepCloneableTest) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return deepCloneableTest;
    }
}
