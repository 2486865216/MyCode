package com.example.dockerboot.Bean;

import com.google.errorprone.annotations.Immutable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * author ye
 * createDate 2022/3/10  13:29
 */
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private Byte sex;
    private Byte deleted;
    private String update_time;
    private String create_time;

    public User() {
    }

    public User(Integer id, String username, String password, Byte sex, Byte deleted, String update_time, String create_time) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.deleted = deleted;
        this.update_time = update_time;
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", deleted='" + deleted + '\'' +
                ", update_time='" + update_time + '\'' +
                ", create_time='" + create_time + '\'' +
                '}';
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
