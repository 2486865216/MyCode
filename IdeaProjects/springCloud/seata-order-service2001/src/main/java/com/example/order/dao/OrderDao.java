package com.example.order.dao;

import com.example.order.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * author ye
 * createDate 2022/8/1  19:51
 */
@Mapper
public interface OrderDao {

    //创建订单
    void create(Order order);

    //修改状态
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}