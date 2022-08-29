package com.example.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.sentinel.myHandle.MyConsumerHandle;
import com.example.springcloud.Moudel.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author ye
 * createDate 2022/7/28  19:13
 */
@RestController
public class FlowLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleByResource")
    public CommonResult<String> byResource() {
        return new CommonResult<>(200, "按资源名测试限流", "(*^_^*)");
    }
    public CommonResult<String> handleByResource(BlockException exception) {
        return new CommonResult<>(444, exception.getClass().getName() + "\t服务不可用");
    }

    @GetMapping("/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult<String> byUrl() {
        return new CommonResult<>(200, "按url测试限流", "(*^_^*)");
    }

    //用户自定义的
    @GetMapping("/consumer")
    @SentinelResource(value = "consumer",
            blockHandlerClass = MyConsumerHandle.class,
            blockHandler = "handleException1")
    public CommonResult<String> consumer() {
        return new CommonResult<>(200, "用户自定义", "(*^_^*)");
    }
}
