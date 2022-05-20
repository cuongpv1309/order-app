package com.example.orderapp.Model;

import java.io.Serializable;

public class Order implements Serializable {

    public int id;
    public int user_id;
    public int coffee_id;
    public String address;
    public int quanity;

    public Order() {
    }

    public Order(int id, int user_id, int coffee_id, String address, int quanity) {
        this.id = id;
        this.user_id = user_id;
        this.coffee_id = coffee_id;
        this.address = address;
        this.quanity = quanity;
    }

    public Order(int user_id, int coffee_id, String address, int quanity) {
        this.user_id = user_id;
        this.coffee_id = coffee_id;
        this.address = address;
        this.quanity = quanity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCoffee_id() {
        return coffee_id;
    }

    public void setCoffee_id(int coffee_id) {
        this.coffee_id = coffee_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getQuanity() {
        return quanity;
    }

    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }
}
