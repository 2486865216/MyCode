package com.yuye.Factory;

/**
 * author ye
 * createDate 2022/1/26  12:44
 */
public abstract class Pizza {
    public abstract void prepara();
    public void pack(){
        System.out.println("打包");
        System.out.println(name);
    }
    private String name;

    public void setName(String name) {
        this.name = name;
    }
}
