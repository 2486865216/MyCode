package com.yuye.Builder;


/**
 * author ye
 * createDate 2022/1/27  12:34
 */
public abstract class HouseBuilder {
    protected House house = new House();

    //建造的流程，抽象的方法
    public abstract void BuildBasic();
    public abstract void BuildWalls();
    public abstract void BuildRoofed();

    //建造房子,将产品返回
    public House buildHouse(){
        return house;
    }
}
