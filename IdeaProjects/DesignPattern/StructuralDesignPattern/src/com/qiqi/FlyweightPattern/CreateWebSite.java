package com.qiqi.FlyweightPattern;

/**
 * author ye
 * createDate 2022/2/6  14:12
 */
public class CreateWebSite extends WebSite{
    private String type = "";//网站发布的形式

    public CreateWebSite(String type) {
        this.type = type;
    }

    @Override
    public void use() {
        System.out.println("网站发布形式:" + type);
    }
}
