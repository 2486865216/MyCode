package com.example.Heap;

/**
 * author ye
 * createDate 2022/2/13  11:19
 * 如何快速的判断是否发生了逃逸分析,大家就看new的对象是否有可能在方法外被调用
 */
public class EscapeAnalysis {
    public EscapeAnalysis obj;
    /**
     * 方法返回EscapeAnalysis对象，发生逃逸
     */
    public EscapeAnalysis getInstance(){
        return obj == null ? new EscapeAnalysis() : obj;
    }
    /**
     * 为成员属性，发生逃逸
     */
    public void setObj(){
        this.obj = new EscapeAnalysis();
    }
    /**
     * 对象的作用域仅在当前方法中有效，没有发生逃逸
     */
    public void useEscapeAnalysis(){
        EscapeAnalysis e = new EscapeAnalysis();
    }
    /**
     * 引用成员变量的值，发生逃逸
     */
    public void useEscapeAnalysis2(){
        EscapeAnalysis e = getInstance();
        //getInstance().xxx()同样会发生逃逸
    }
}
