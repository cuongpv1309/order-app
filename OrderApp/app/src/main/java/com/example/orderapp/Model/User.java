package com.example.orderapp.Model;

import java.io.Serializable;

public class User implements Serializable {

    public int id;
    public String name;
    public String username;
    public String password;
    public int role;

    public User() {
    }

    public User(int id, String name, String username, String password, int role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String name, String username, String password, int role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
