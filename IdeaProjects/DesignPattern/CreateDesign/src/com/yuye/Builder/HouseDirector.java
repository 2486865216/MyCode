package com.yuye.Builder;

/**
 * author ye
 * createDate 2022/1/27  12:40
 */
//指挥者，指定制作流程
public class HouseDirector {
    HouseBuilder houseBuilder = null;

    //构造器传入
    public HouseDirector(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }
    //setter传入

    public void setHouseBuilder(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    //如何造房子交给指挥者
    public House constructorHouse(){
        houseBuilder.BuildBasic();
        houseBuilder.BuildWalls();
        houseBuilder.BuildRoofed();
        return houseBuilder.buildHouse();
    }
}
