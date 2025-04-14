package com.example.tmstraining.repository;

import com.example.tmstraining.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByUserId(int userId);

    Optional<Order> findFirstByUserIdOrderByOrderDateDesc(int userId);
}
