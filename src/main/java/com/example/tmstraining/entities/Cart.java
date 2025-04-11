package com.example.tmstraining.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
    private List<CartDetails> listCartDetails;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
