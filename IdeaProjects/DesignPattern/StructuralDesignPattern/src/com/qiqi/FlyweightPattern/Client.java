package com.qiqi.FlyweightPattern;

/**
 * author ye
 * createDate 2022/2/6  14:19
 */
public class Client {
    public static void main(String[] args) {
        //创建一个工厂类
        WebSiteFactory webSiteFactory = new WebSiteFactory();
        //新闻形式
        WebSite webSite = webSiteFactory.getWebSite("新闻");
        webSite.use();
        //博客形式
        WebSite webSite2 = webSiteFactory.getWebSite("博客");
        webSite2.use();

        WebSite webSite3 = webSiteFactory.getWebSite ("博客");
        webSite3.use();

        System.out.println("发布形式总数:"+webSiteFactory.getCountWeb());
    }
}
