package com.example.springcloud.Dao;

import com.example.springcloud.Moudel.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * author ye
 * createDate 2022/5/16  9:55
 */
@Mapper
public interface PaymentDao {

    public int create(Payment payment);

    public Payment getPayment(@Param("id") Integer id);
}
