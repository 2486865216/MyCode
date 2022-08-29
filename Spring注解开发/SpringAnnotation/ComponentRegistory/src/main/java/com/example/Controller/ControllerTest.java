package com.example.Controller;

import com.example.Service.ServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * author ye
 * createDate 2022/3/14  15:08
 */
@Controller
public class ControllerTest {
    @Autowired
    private ServiceTest serviceTest;

    @Override
    public String toString() {
        return "ControllerTest{" +
                "serviceTest=" + serviceTest +
                '}';
    }
}
