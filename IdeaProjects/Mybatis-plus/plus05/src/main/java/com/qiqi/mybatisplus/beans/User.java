package com.qiqi.mybatisplus.beans;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;

public class User {
    private Integer id;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String name;

    @TableLogic
    private Integer logic_flag;

    public User() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogic_flag(Integer logic_flag) {
        this.logic_flag = logic_flag;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getLogic_flag() {
        return logic_flag;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", logic_flag=" + logic_flag +
                '}';
    }
}
