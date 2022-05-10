package com.qiqi.MementoPattern;

/**
 * author ye
 * createDate 2022/2/8  13:52
 */
public class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
