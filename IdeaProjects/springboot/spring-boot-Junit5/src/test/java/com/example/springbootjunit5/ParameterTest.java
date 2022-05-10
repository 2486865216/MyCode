package com.example.springbootjunit5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ParameterTest {
    @ParameterizedTest
    @DisplayName("参数测试")
    @ValueSource(ints = {1,2,3,4,5})
    void testParameter(int i){
        System.out.println(i);
    }
}
