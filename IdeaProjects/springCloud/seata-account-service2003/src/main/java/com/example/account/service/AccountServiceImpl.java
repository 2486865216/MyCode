package com.example.account.service;

import com.example.account.dao.AccountDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * author ye
 * createDate 2022/8/2  19:52
 */
@Service
public class AccountServiceImpl implements AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountDao accountDao;

    //加减 库存
    @Override
    public void decrease(Long userId, BigDecimal money) {
        LOGGER.info("account开始减钱");
        accountDao.decrease(userId, money);
        LOGGER.info("account开始减钱完成");
    }
}
