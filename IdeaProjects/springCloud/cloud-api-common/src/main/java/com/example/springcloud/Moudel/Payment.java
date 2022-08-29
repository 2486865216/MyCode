package com.example.springcloud.Moudel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * author ye
 * createDate 2022/5/16  13:16
 */
@Data
public class Payment implements Serializable {
    private Integer id;
    private String serial;

    public Payment() {
    }

    public Payment(Integer id, String serial) {
        this.id = id;
        this.serial = serial;
    }
}
