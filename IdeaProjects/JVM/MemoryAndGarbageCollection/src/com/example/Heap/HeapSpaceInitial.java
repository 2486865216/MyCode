package com.example.Heap;

/**
 * author ye
 * createDate 2022/2/12  13:08
 * - “-Xms用于表示堆区的起始内存,等价于-xx: InitialHeapsize
 *   - -X是JVM运行参数
 *   - ms: memory start
 * - “-Xmx”则用于表示堆区的最大内存,等价于-Xx:MaxHeapsize
 * - 一旦堆区中的内存大小超过“-Xmx"所指定的最大内存时,将会抛出OutOfMemoryError异常
 * - 通常会将-Xms和-Xmx两个参数配置相同的值,其目的是为了
 * 能够在java垃圾回收机制清理完堆区后不需要重新分隔计算堆区的大小,
 * 从而提高性能。
 *
 * -Xms256k -Xmx256k
 *
 * - 默认情况下,初始内存大小:物理电脑内存大小/64
 * - 最大内存大小:物理电脑内存大小/4
 *
 * 查看设置的参数 jps jstat gc 进程号
 * -XX:+PrintGCDetails
 */
public class HeapSpaceInitial {
    public static void main(String[] args) {
        //返回java虚拟机中堆内存总量
        long initialMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        //返回java虚拟机中最大堆内存量
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        System.out.println("-Xms :"+initialMemory+"M");
        System.out.println("-Xmx :"+maxMemory+"M");

        System.out.println("系统内存大小为：" + initialMemory * 64.0 / 1024 + " G");
        System.out.println("系统内存大小为：" + maxMemory * 4.0 / 1024 + " G");

    }
}
