package com.example.springbootjunit5;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

/**
 * JUnit5常用注解
 * JUnit5的注解与JUnit4的注解有所变化
 * @Test：表示方法是测试方法。但是与JUnit4的@Test不同，
 *      他的职责非常单一不能声明任何属性，拓展的测试将会由Jupiter提供额外测试
 * @ParameterizedTest：表示方法是参数化测试，下方会有详细介绍
 * @RepeatedTest：表示方法可重复执行，下方会有详细介绍
 * @DisplayName ：为测试类或者测试方法设置展示名称
 * @BeforeEach：表示在每个单元测试之前执行
 * @AfterEach：表示在每个单元测试之后执行
 * @BeforeAll ：表示在所有单元测试之前执行
 * @AfterAlI：表示在所有单元测试之后执行
 * @Tag：表示单元测试类别，类似于JUnit4中的@Categories
 * @Disabled ：表示测试类或测试方法不执行，类似于JUnit4中的@lgnore
 * @Timeout ：表示测试方法运行如果超过了指定时间将会返回错误
 * @ExtendWith：为测试类或测试方法提供扩展类引用
 */
@DisplayName("Junit5功能测试类")
/**
 * @BootstrapWith(SpringBootTestContextBootstrapper.class)
 * @ExtendWith(SpringExtension.class)
 */
@SpringBootTest
public class Junit5Test {
//@DisplayName ：为测试类或者测试方法设置展示名称
    @Test
    @DisplayName("测试DisplayName注解")
    void testDisplay1(){
        System.out.println(1);
    }
//@Disabled ：表示测试类或测试方法不执行，类似于JUnit4中的@lgnore
    @Disabled
    @Test
    @DisplayName("测试DisplayName注解")
    void testDisplay2(){
        System.out.println(2);
    }
//@Timeout ：表示测试方法运行如果超过了指定时间将会返回错误
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    @Test
    void testTimeout() throws InterruptedException {
        Thread.sleep(6000);
    }
//@RepeatedTest：表示方法可重复执行，下方会有详细介绍
    @RepeatedTest(5)
    @Test
    void testRepeatedTest(){
        System.out.println(123);
    }

    @BeforeEach
    void testBeforeEach(){
        System.out.println("BeforeEach!");
    }

    @AfterEach
    void testAfterEach(){
        System.out.println("AfterEach!");
    }

    @BeforeAll
    static void testBeforeAll(){
        System.out.println("BeforeAll!");
    }

    @AfterAll
    static void testAfterAll(){
        System.out.println("AfterAll!");
    }
}
