package com.yuye.Builder;

import org.junit.Test;

/**
 * author ye
 * createDate 2022/1/27  12:44
 */
public class Client {
    @Test
    public void test() {
        //该普通房子
        CommonHouse commonHouse = new CommonHouse();
        //指挥者
        HouseDirector houseDirector = new HouseDirector(commonHouse);
        //完成，返回产品
        House house = houseDirector.constructorHouse();
        System.out.println(house);

        //高楼
        HighHouse highHouse = new HighHouse();
        //指挥者已有
        houseDirector.setHouseBuilder(highHouse);

        House house1 = houseDirector.constructorHouse();

        System.out.println(house1);
    }
}
