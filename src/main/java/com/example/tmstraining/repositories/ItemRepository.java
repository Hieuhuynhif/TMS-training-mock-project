package com.example.tmstraining.repositories;

import com.example.tmstraining.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
