package com.example.tmstraining.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int quantity;
    private Date dateAdded;
}
