package com.qiqi.CompositePattern;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * author ye
 * createDate 2022/2/6  13:00
 */
public class MapTest {
    @Test
    public void test() {
        /* 1.Map 就是一个抽象的构建（类似我们的Component）
            2。HashMap是一个中间的构建(Composite)，实现/继承了相关方法
            put, putall
            3. Node 是HashMap的静态内部类，类似Leaf叶子节点，这里就没有put，
                putall

              static class Node<K,V> implements Map.Entry<K,V>
         */
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "hello");//直接存放叶子节点

        Map<Integer, String> map1 = new HashMap<>();
        map1.put(2, "word");
        map1.put(3, "java");
        map.putAll(map1);
        System.out.println(map);
    }
}
