package com.example.account.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * author ye
 * createDate 2022/8/2  19:43
 */
@Mapper
public interface AccountDao {
    //扣减库存
    void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
