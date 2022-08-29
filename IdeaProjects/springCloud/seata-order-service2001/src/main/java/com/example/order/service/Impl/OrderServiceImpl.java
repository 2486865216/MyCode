package com.example.order.service.Impl;

import com.example.order.dao.OrderDao;
import com.example.order.domain.Order;
import com.example.order.service.AccountService;
import com.example.order.service.OrderService;
import com.example.order.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author ye
 * createDate 2022/8/1  20:08
 */
@Service
@Slf4j
@GlobalTransactional(name = "test", rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private AccountService accountService;

    @Autowired
    private StorageService storageService;

    @Override
    public void create(Order order) {
        log.info("================开始创建订单================");
        orderDao.create(order);
        log.info("================创建订单完成================");

        log.info("================开始调用库存================");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("================调用库存完成================");

        log.info("================开始调用账户================");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("================调用账户完成================");

        //修改订单的状态
        log.info("================修改订单状态================");
        orderDao.update(order.getUserId(), 1);
        log.info("================修改订单状态成功================");

        log.info("下单成功(*^_^*)");
    }
}
