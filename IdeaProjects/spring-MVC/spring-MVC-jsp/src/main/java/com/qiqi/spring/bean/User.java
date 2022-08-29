package com.qiqi.spring.bean;

/**
 * author ye
 * createDate 2022/3/19  15:56
 */
public class User {
    private Integer id;
    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    public User() {
    }
}
