package com.yuye.Prototype;

/**
 * author ye
 * createDate 2022/1/27  9:35
 */
public class Client {
    public static void main(String[] agrs) throws CloneNotSupportedException {
        System.out.println("原型模式完成对象的创建");
        Sheep sheep = new Sheep("tom", 18);
        sheep.setFriend(new Sheep("jack",20));
        Sheep sheep2 =(Sheep) sheep.clone();
        Sheep sheep3 =(Sheep) sheep.clone();
        Sheep sheep4 =(Sheep) sheep.clone();
        Sheep sheep5 =(Sheep) sheep.clone();

        System.out.println(sheep+"==="+sheep.getFriend().hashCode());
        System.out.println(sheep2+"==="+sheep2.getFriend().hashCode());
        System.out.println(sheep3+"==="+sheep3.getFriend().hashCode());
        System.out.println(sheep4+"==="+sheep4.getFriend().hashCode());
        System.out.println(sheep5+"==="+sheep5.getFriend().hashCode());

    }
}
