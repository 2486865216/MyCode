package com.example.account.controller;

import cn.hutool.core.math.Money;
import com.example.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * author ye
 * createDate 2022/8/2  19:55
 */
@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/account/decrease")
    public String decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money) {
        accountService.decrease(userId, money);
        return "扣减库存成功";
    }
}
