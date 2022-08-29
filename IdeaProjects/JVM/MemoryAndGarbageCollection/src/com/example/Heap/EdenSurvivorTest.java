package com.example.Heap;

/**
 * author ye
 * createDate 2022/2/12  14:27
 * -Xms600m -Xmx600m
 * -XX:NewRatio=2配置新生代与老年代在堆结构的占比 默认值是2
 * -XX:SurvivorRatio=8 设置新生代中Eden去与survivor区的比例  默认是8
 */
public class EdenSurvivorTest {
    public static void main(String[] args) {
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
