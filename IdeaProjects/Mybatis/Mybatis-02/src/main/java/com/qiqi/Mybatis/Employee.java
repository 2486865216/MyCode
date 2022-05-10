package com.qiqi.Mybatis;

public class Employee {
    private Integer id;
    private String lastname;
    private Integer gender;
    private String email;
    private Department dept;

    public Employee() {
    }

    public Employee(Integer id, String lastname, Integer gender, String email) {
        this.id = id;
        this.lastname = lastname;
        this.gender = gender;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public Integer getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public Department getDept() {
        return dept;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", dept=" + dept +
                '}';
    }
}
