package com.example.tmstraining.repository;

import com.example.tmstraining.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    Item findByName(String name);
}
