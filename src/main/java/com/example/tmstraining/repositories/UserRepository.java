package com.example.tmstraining.repositories;

import com.example.tmstraining.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query
    User findByUsername(String username);
}
