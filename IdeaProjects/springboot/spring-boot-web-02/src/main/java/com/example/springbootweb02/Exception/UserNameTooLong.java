package com.example.springbootweb02.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "用户名太长!")
public class UserNameTooLong extends RuntimeException{
    public UserNameTooLong() {
    }

    public UserNameTooLong(String message) {
        super(message);
    }
}
