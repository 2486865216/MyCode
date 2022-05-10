package com.yuye.Builder;

/**
 * author ye
 * createDate 2022/1/27  12:38
 */
public class CommonHouse extends HouseBuilder{
    @Override
    public void BuildBasic() {
        house.setBasic("CommonHouse");
        System.out.println("Common BuildBasic");
    }

    @Override
    public void BuildWalls() {
        house.setWall("CommonHouse");
        System.out.println("Common BuildWalls");
    }

    @Override
    public void BuildRoofed() {
        house.setRoofed("CommonHouse");
        System.out.println("Common BuildRoofed");
    }
}
