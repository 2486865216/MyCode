package com.example.springwebflux.reactorDemo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * author ye
 * createDate 2022/4/19  13:01
 */

/**
 * (⊥)响应式编程操作中，Reactor是满足Reactive规范框架
 * (2)Reactor有两个核心类，Mono和月lux,这两个类实现接口Publisher,提供丰富操作
 * 符。Flux对象实现发布者，返回N个元素；Mono实现发布者，返回0或者1个元素
 * (3)FIux和Mono都是数据流的发布者，使用FIux和Mono都可以发出三种数据信号：
 * 元素值，错误信号，完成信号，错误信号和完成信号都代表终止信引
 */
public class TestReactor {
    public static void main(String[] args) {
        //just方法直接声明
        //Flux.just(1,2,3,4);
        //Mono.just(1);

        Flux.just(1,2,3,4).subscribe(System.out::println);
        Mono.just(1).subscribe(System.out::println);

        //其它方法
        Integer[] array = {1,2,3,4};
        Flux.fromArray(array);
        List<Integer> list = Arrays.asList(array);
        Flux.fromIterable(list);

        Stream<Integer> stream = list.stream();
        Flux.fromStream(stream);
    }
}
