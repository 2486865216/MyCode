package com.qiqi.FlyweightPattern;


import com.sun.jndi.ldap.pool.Pool;

import java.util.HashMap;
import java.util.Map;

/**
 * author ye
 * createDate 2022/2/6  14:14
 */
//网站工厂类。根据需求返回一个网站
public class WebSiteFactory {
    //集合，充当池的作用
    Map<String, CreateWebSite> pool = new HashMap<>();

    //根据网站的类型返回一个网站，如果没有就创建一个，放入池中，并返回
    public WebSite getWebSite(String type){
        if (!pool.containsKey(type)){
            pool.put(type,new CreateWebSite(type));
        }
        return (WebSite) pool.get(type);
    }
    //获取网站分类的总数
    public int getCountWeb(){
        return pool.size();
    }
}
