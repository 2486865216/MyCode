package com.example.GarbageCollection.Reference;

/**
 * author ye
 * createDate 2022/2/16  12:31
 */
public class User{
    public Integer id;
    public String name;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
