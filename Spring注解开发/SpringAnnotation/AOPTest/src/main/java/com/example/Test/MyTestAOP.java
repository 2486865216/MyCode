package com.example.Test;

import com.example.Bean.MathCalculator;
import com.example.Config.MyAOPTest;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * author ye
 * createDate 2022/3/15  18:30
 */
public class MyTestAOP {
    @Test
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyAOPTest.class);
        MathCalculator bean = context.getBean(MathCalculator.class);
        bean.div(1, 1);
    }
}
