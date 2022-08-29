package com.example.Config;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * author ye
 * createDate 2022/3/17  8:28
 */
@Component
public class TestEventListener {
    @EventListener(classes = {ApplicationEvent.class})
    public void listen(ApplicationEvent event){
        System.out.println("@EventListener : " + event);
    }
}
