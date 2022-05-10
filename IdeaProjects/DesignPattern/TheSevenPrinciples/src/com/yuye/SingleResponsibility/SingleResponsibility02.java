package com.yuye.SingleResponsibility;

/**
 * author ye
 * createDate 2022/1/25  10:07
 */
public class SingleResponsibility02 {
    public static void main(String[] args) {
        Vehicle02 vehicle02 = new Vehicle02();
        vehicle02.run("Car");
        vehicle02.runAir("The plane");
    }
}
//没有在类上遵守单一职责，但在方法上遵守单一职责原则
class Vehicle02{
    public void run(String vehicle){
        System.out.println(vehicle + " is running on load.....");
    }
    public void runAir(String vehicle){
        System.out.println(vehicle + " is running on sky.....");
    }
}
