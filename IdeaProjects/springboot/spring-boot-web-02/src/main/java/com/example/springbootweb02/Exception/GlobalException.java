package com.example.springbootweb02.Exception;

import org.apache.commons.logging.Log;
import org.springframework.boot.logging.DeferredLog;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler({NullPointerException.class, ArithmeticException.class})
    public String exception(Exception e){
        Log log = new DeferredLog();
        log.error("异常是{}", e);
        return "login";
    }
}
