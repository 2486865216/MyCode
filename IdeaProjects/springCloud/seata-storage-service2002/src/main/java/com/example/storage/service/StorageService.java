package com.example.storage.service;

import org.springframework.stereotype.Service;

/**
 * author ye
 * createDate 2022/8/2  19:51
 */
@Service
public interface StorageService {
    void decrease(Long productId, Integer count);
}
