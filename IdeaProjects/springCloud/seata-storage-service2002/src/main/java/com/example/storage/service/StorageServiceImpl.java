package com.example.storage.service;

import com.example.storage.dao.StorageDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author ye
 * createDate 2022/8/2  19:52
 */
@Service
public class StorageServiceImpl implements StorageService{
    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);

    @Autowired
    private StorageDao storageDao;

    //加减 库存
    @Override
    public void decrease(Long productId, Integer count) {
        LOGGER.info("storage开始减库存");
        storageDao.decrease(productId, count);
        LOGGER.info("storage开始减库存完成");
    }
}
