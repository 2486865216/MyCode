package com.qiqi.spring.controller.bean;

public class Employee {
    private Integer id;
    private String lastname;
    private String email;
    private Integer gender;

    public Integer getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Employee() {
    }

    public Employee(Integer id, String lastname, String email, Integer gender) {
        this.id = id;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
    }
}
