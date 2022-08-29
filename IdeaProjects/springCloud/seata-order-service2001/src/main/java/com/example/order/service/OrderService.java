package com.example.order.service;

import com.example.order.domain.Order;
import org.springframework.stereotype.Service;

/**
 * author ye
 * createDate 2022/8/1  20:07
 */
@Service
public interface OrderService {
    void create(Order order);
}
