package com.example.tmstraining.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int quantity;
    private Date dateAdded;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Item item;

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}
