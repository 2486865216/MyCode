package com.qiqi.ChainOfResponsibilityPattern;

/**
 * author ye
 * createDate 2022/2/9  15:46
 */
public class CollageApprover extends Approver{
    public CollageApprover(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getPrice()>5000 && request.getPrice()<=10000){
            System.out.println("请求编号:"+request.getId()+"被"+this.name+"处理");
        }else{
            approver.processRequest(request);
        }
    }
}
