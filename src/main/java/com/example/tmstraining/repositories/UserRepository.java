package com.example.tmstraining.repositories;

import com.example.tmstraining.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
