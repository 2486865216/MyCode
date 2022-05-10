package com.qiqi.ChainOfResponsibilityPattern;

/**
 * author ye
 * createDate 2022/2/9  15:39
 */
public abstract class Approver {
    //下一个处理者
    Approver approver;
    public String name;//名字

    public Approver(String name) {
        this.name = name;
    }

    public void setApprover(Approver approver) {
        this.approver = approver;
    }
    //处理审批请求的方法，得到一个请求，处理是子类完成
    public abstract void processRequest(PurchaseRequest request);
}
