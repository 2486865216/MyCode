package com.example.json_ajax.person;

public class oersonjson {
    private int id;
    private String name;

    public oersonjson() {
    }

    public oersonjson(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "oersonjson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
