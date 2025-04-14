package com.example.tmstraining.repository;

import com.example.tmstraining.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUserId(int userId);
}
