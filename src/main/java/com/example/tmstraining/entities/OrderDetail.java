package com.example.tmstraining.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int quantity;

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }
}
