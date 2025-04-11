package com.example.tmstraining.repositories;

import com.example.tmstraining.entities.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetails, Integer> {
}
