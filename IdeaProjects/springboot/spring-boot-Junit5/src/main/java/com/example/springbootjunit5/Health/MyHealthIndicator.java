package com.example.springbootjunit5.Health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MyHealthIndicator extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if (1==1){
            builder.up();
            map.put("code",5000);
            map.put("message", "success");
        }else {
            builder.down();
        }
        builder.withDetail("testHealth","success")
                .withDetail("code",52222)
                .withDetails(map);
    }
}
