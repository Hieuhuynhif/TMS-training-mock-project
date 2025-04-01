package com.example.tmstraining.repositories;

import com.example.tmstraining.entities.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {
}
