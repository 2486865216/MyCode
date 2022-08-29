package com.example.springbootjunit5;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AssumptionsTest {
    @Test
    @DisplayName("测试前置条件")
    void testAssumptions(){
        Assumptions.assumeTrue(false, "结果不是true");
        System.out.println(11111111);
    }
}
