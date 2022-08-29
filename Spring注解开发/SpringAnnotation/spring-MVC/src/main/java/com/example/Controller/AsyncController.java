package com.example.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * author ye
 * createDate 2022/3/17  13:38
 */
@Controller
public class AsyncController {

    @ResponseBody
    @RequestMapping("/createOrder")
    public DeferredResult<Object> deferredResult(){
        DeferredResult<Object> deferredResult = new DeferredResult<Object>((long)3000, "create fail");
        OrderQueue.save(deferredResult);
        return deferredResult;
    }

    @ResponseBody
    @RequestMapping("/create")
    public String result(){
        String order = UUID.randomUUID().toString();
        OrderQueue.get().setResult(order);
        return "success" + order;
    }
    @ResponseBody
    @RequestMapping("/async")
    /**
     * 1、控制器返回Ca11ab1e
     * 2、Spring异步处理，将Callable提交到TaskExecutor使用一个隔离的线程进行执行
     * 3、Dispatcherservlet和所有的Filter退出web容器的线程，但是response保持打开状态；
     * 4、Callable返回结果，SpringMVC将请求重新派发给容器，恢复之前的处理；
     * 5、根据Cal1able返回的结果。SpringMVC继续进行视图渲染流程等（从收请求-视图渲染）
     *
     * Callable的之前的返回值就是目标方法的返回值
     *
     * 异步的拦截器：
     *  1)、原生API的AsyncListener
     *  2)、SpringMVC:实现AsyncHandlerInterceptor;
     */
    public Callable<String> async(){
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                return "callable async";
            }
        };
        return callable;
    }
}
