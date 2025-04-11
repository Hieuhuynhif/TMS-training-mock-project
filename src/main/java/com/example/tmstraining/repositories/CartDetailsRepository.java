package com.example.tmstraining.repositories;

import com.example.tmstraining.entities.CartDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartDetailsRepository extends JpaRepository<CartDetails, Integer> {
    @Query
    CartDetails findByCartIdAndItemId(int cartId, int itemId);
}
