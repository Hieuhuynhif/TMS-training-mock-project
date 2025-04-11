package com.example.tmstraining.repositories;

import com.example.tmstraining.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query
    Cart findByUserId(int userId);

}
