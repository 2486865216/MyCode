package com.yuye.Builder;

/**
 * author ye
 * createDate 2022/1/27  12:39
 */
public class HighHouse extends HouseBuilder{
    @Override
    public void BuildBasic() {
        house.setBasic("HighHouse");
        System.out.println("High BuildBasic");
    }

    @Override
    public void BuildWalls() {
        house.setWall("HighHouse");
        System.out.println("High BuildWalls");
    }

    @Override
    public void BuildRoofed() {
        house.setRoofed("HighHouse");
        System.out.println("High BuildRoofed");
    }
}
