package com.qiqi.Bridge;

/**
 * author ye
 * createDate 2022/2/6  10:55
 */
public class Client {
    public static void main(String[] args) {
        //折叠式手机
        Brand brand = new XiaoMi();
        Phone phone = new FoldedPhone(brand);
        phone.open();
        phone.close();
        phone.call();

        Brand brand1 = new Vivo();
        Phone phone1 = new FoldedPhone(brand1);
        phone1.open();
        phone1.close();
        phone1.call();

        Phone phone2 = new UPRightPhone(new XiaoMi());
        phone2.open();
        phone2.close();
        phone2.call();

        Phone phone3 = new UPRightPhone(new Vivo());
        phone3.open();
        phone3.close();
        phone3.call();
    }
}
