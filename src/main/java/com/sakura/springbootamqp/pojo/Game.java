package com.sakura.springbootamqp.pojo;

import java.io.Serializable;

/**
 * @Author: Sakura
 * @Description:
 * @Date: 2018/11/23 17:33
 */
public class Game implements Serializable {

    private String name;

    private Double price;

    public Game() {
    }

    public Game(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
