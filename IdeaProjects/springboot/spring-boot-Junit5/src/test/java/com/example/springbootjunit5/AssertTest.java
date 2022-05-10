package com.example.springbootjunit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("断言测试")
public class AssertTest {
    int cal(int i, int j){
        return i + j;
    }

    /**
     * 断言：前面的断言失败，后面的代码都不会执行
     */
    @Test
    @DisplayName("测试简单断言")
    void testAssert1(){
        int cal = cal(2,3);
        //Assertions.assertEquals(6, cal);
        Assertions.assertEquals(5, cal, "结果不同");

        Object obj1 = new Object();
        Object obj2 = new Object();
        //Assertions.assertSame(obj1, obj2);
        Assertions.assertSame(obj1, obj2, "对象不是同一个!");
    }

    @Test
    @DisplayName("Test Array")
    void testArray(){
        Assertions.assertArrayEquals(new int[]{2,2},new int[]{1,2},"数组不一样");
    }

    @Test
    @DisplayName("组合断言")
    /**
     * 所有断言都成功才能往下走
     */
    void testAll(){
        Assertions.assertAll("test",
                ()->Assertions.assertTrue(true),
                ()->Assertions.assertEquals(1, 2));
    }

    @Test
    @DisplayName("Exception")
    void testException(){
        Assertions.assertThrows(ArithmeticException.class, ()->{int i = 10/2;},
                "业务逻辑居然正常运行?");
    }

    @Test
    @DisplayName("快速失败")
    void testFail(){
        if (2 == 2){
            Assertions.fail("测试失败");
        }
    }
}
