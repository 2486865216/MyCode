package com.qiqi.ChainOfResponsibilityPattern;

/**
 * author ye
 * createDate 2022/2/9  15:50
 */
public class Client {
    public static void main(String[] args) {
        PurchaseRequest purchaseRequest = new PurchaseRequest(1, 9999, 1);
        DepartmentApprover departmentApprover = new DepartmentApprover("系主任");
        CollageApprover collageApprover = new CollageApprover("院长");
        SchoolApprover schoolApprover = new SchoolApprover("校长");

        //设置下级审批人员
        departmentApprover.setApprover(collageApprover);
        collageApprover.setApprover(schoolApprover);

        departmentApprover.processRequest(purchaseRequest);
    }
}
