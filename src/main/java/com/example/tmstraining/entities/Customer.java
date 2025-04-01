package com.example.tmstraining.entities;

import jakarta.persistence.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String username;
    private String password;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
