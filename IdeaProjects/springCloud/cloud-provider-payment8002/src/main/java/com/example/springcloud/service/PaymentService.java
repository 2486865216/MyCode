package com.example.springcloud.service;

import com.example.springcloud.Moudel.Payment;

/**
 * author ye
 * createDate 2022/5/16  10:05
 */
public interface PaymentService {
    int create(Payment payment);
    Payment getPayment(Integer id);
}
