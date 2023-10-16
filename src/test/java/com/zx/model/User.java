package com.zx.model;

import java.io.Serializable;

/**
 * @program: myStudy
 * @description: 测试对象
 * @author: zhou  xun
 * @create: 2022-09-21 15:27
 */

public class User implements Serializable {
    private String name;
    private Integer sex;

    public User() {
    }

    public User(String name, Integer sex) {
        this.name = name;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                '}';
    }
}
