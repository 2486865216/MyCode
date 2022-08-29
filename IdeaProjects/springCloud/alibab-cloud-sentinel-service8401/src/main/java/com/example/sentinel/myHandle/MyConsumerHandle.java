package com.example.sentinel.myHandle;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.springcloud.Moudel.CommonResult;

/**
 * author ye
 * createDate 2022/7/28  19:27
 */
public class MyConsumerHandle {
    public static CommonResult<String> handleException1(BlockException exception){
        return new CommonResult<>(444, "用户自定义的异常处理============1");
    }
    public static CommonResult<String> handleException2(BlockException exception){
        return new CommonResult<>(444, "用户自定义的异常处理============2");
    }
}
