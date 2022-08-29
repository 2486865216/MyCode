package com.qiqi.mybatisplus.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangyuye
 * @since 2021-12-24
 */
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String lastname;

    private String email;

    private String gender;

    private Integer age;


    @Version
    private Integer version;

    public Employee() {
    }

    public Employee(Integer id, String lastname, String email, String gender, Integer age) {
        this.id = id;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.age = age;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
        "id=" + id +
        ", lastname=" + lastname +
        ", email=" + email +
        ", gender=" + gender +
        ", age=" + age +
        "}";
    }
}
