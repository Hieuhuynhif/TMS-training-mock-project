package com.example.tmstraining.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "cart")
    private List<CartDetail> cartDetails;

    public int getId() {
        return id;
    }


}
