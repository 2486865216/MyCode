package com.example.Test;

import com.example.Config.ExtensionConfig;
import com.example.Config.MyApplicationListener;
import com.example.Config.TestEventListener;
import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * author ye
 * createDate 2022/3/16  13:15
 */
public class TestExtension {
    @Test
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ExtensionConfig.class);
        context.publishEvent(new ApplicationEvent(new String("发布事件")) {
        });
        context.close();
    }
}
