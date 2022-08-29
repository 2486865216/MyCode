package com.example.springcloud.service.serviceImpl;

import com.example.springcloud.Dao.PaymentDao;
import com.example.springcloud.Moudel.Payment;
import com.example.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * author ye
 * createDate 2022/5/16  10:07
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPayment(Integer id) {
        return id == null ? null : paymentDao.getPayment(id);
    }
}
