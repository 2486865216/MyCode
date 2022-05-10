package com.yuye.SingleResponsibility;

/**
 * author ye
 * createDate 2022/1/25  10:03
 */
public class SingleResponsibility01 {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        vehicle.run("汽车");
        vehicle.run("飞机");
    }
}
//违反了单一职责原则
class Vehicle{
    public void run(String vehicle){
        System.out.println(vehicle + " is running.....");
    }
}
