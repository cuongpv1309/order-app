package com.example.orderapp.Model;

import java.io.Serializable;

public class Ordertmp implements Serializable {

    public String coffee_name;
    public String quanity;
    public String total;
    public String address;

    public Ordertmp() {
    }

    public Ordertmp(String coffee_name, String quanity, String total, String address) {
        this.coffee_name = coffee_name;
        this.quanity = quanity;
        this.total = total;
        this.address = address;
    }

    public String getCoffee_name() {
        return coffee_name;
    }

    public void setCoffee_name(String coffee_name) {
        this.coffee_name = coffee_name;
    }

    public String getQuanity() {
        return quanity;
    }

    public void setQuanity(String quanity) {
        this.quanity = quanity;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
