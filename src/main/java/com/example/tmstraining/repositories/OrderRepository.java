package com.example.tmstraining.repositories;

import com.example.tmstraining.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
