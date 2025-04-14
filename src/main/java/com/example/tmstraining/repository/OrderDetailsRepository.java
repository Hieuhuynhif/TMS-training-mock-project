package com.example.tmstraining.repository;

import com.example.tmstraining.entities.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
    List<OrderDetails> findByItemId(int itemId);
}
