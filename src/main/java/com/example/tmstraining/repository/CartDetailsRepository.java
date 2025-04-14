package com.example.tmstraining.repository;

import com.example.tmstraining.entities.CartDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartDetailsRepository extends JpaRepository<CartDetails, Integer> {
    CartDetails findByCartIdAndItemId(int cartId, int itemId);

    List<CartDetails> findByItemId(int itemId);

    void deleteAllByCartId(int cartId);

    Optional<CartDetails> findByCartIdAndId(int cartId, int id);
}
