package com.qiqi.mybatisplus;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @TableName:指定表名
 *      MP会默认使用实体类的类名到数据库中找对应的表
 */
@TableName(value = "employee")
public class Employee {
    /**
     * @TableId:
     *      value:指定表中的主键列的列名，如果实体类属性名与列名一致，可以省略
     *      type：指定主键策略
     */
    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;

    @TableField(value = "lastname")
    private String  lastname;
    private String email;
    private Integer gender;
    private Integer age;

    @TableField(exist = false)
    private Double salary;

    public Employee() {
    }

    public Employee(Integer id, String lastname, String email, Integer gender, Integer age) {
        this.id = id;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

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

    public Integer getAge() {
        return age;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
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

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }
}
