package com.example.Dao;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

/**
 * author ye
 * createDate 2022/3/14  15:08
 */
@Repository
public class DaoTest {
    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
