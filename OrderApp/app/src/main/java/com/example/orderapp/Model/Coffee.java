package com.example.orderapp.Model;

import java.io.Serializable;

public class Coffee implements Serializable {

    public int id;
    public int img;
    public String name;
    public String price;
    public String des;

    public Coffee() {
    }

    public Coffee(int id, int img, String name, String price, String des) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.price = price;
        this.des = des;
    }

    public Coffee(int img, String name, String price, String des) {
        this.img = img;
        this.name = name;
        this.price = price;
        this.des = des;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
