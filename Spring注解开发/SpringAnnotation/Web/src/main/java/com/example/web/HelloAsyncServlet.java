package com.example.web;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * author ye
 * createDate 2022/3/17  13:23
 */
@WebServlet(value = "/async", asyncSupported = true)
public class HelloAsyncServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //I、支持异步处理asyncSupported=true
        //2、开启异步模式
        resp.getWriter().write("lllll");
        System.out.println(Thread.currentThread());
        AsyncContext asyncContext = req.startAsync();
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                try {
                    sayHello();
                    asyncContext.complete();
                    //获取到异步上下文
                    AsyncContext asyncContext1 = req.getAsyncContext();
                    //4、获取响应
                    ServletResponse response = asyncContext.getResponse();
                    response.getWriter().write("hello async...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void sayHello() throws InterruptedException {
        System.out.println(Thread.currentThread() + "processing...");
        Thread.sleep(3000);
        System.out.println(Thread.currentThread());
    }
}
