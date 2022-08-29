package com.example.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author ye
 * createDate 2022/7/8  9:18
 */
@Service
public interface LoadBalance {
    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
