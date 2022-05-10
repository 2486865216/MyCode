package com.example.Bean;

import javafx.fxml.Initializable;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.ResourceBundle;


/**
 * author ye
 * createDate 2022/3/15  8:45
 */
public class Cat implements InitializingBean, DisposableBean {
    public Cat() {
        System.out.println("Cat Constructor");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("Cat init...");
    }
    public void destroy() throws Exception {
        System.out.println("Cat destroy...");
    }

}
