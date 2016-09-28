package com.practice.springboot.p03.entity;

import java.io.Serializable;

public class User implements Serializable {
    private Long id;
    private String name;
    private Integer age;
    // 省略getter和setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public String toString(){
        return this.id+"_"+this.name+"_"+this.age;
    }
}