package com.example.order.controller;

import com.example.order.domain.Order;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author ye
 * createDate 2022/8/1  20:24
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    public String creat(Order order) {
        orderService.create(order);
        return "success(*^_^*)";
    }
}
