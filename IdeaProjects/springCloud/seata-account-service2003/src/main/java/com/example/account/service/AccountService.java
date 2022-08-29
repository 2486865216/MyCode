package com.example.account.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * author ye
 * createDate 2022/8/2  19:51
 */
@Service
public interface AccountService {
    void decrease(Long userId, BigDecimal money);
}
