package com.example.tmstraining.service;

import com.example.tmstraining.dtos.cart.ItemCartRequest;
import com.example.tmstraining.entities.Cart;
import com.example.tmstraining.entities.CartDetails;
import com.example.tmstraining.entities.Item;
import com.example.tmstraining.exception.CartDetailsNotFoundException;
import com.example.tmstraining.exception.ItemNotFoundException;
import com.example.tmstraining.exception.QuantityLessThanOneException;
import com.example.tmstraining.repository.CartDetailsRepository;
import com.example.tmstraining.repository.CartRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartDetailsRepository cartDetailRepository;

    public CartService(CartRepository cartRepository, CartDetailsRepository cartDetailRepository) {
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
    }

    public Cart findByUserId(int userId) {
        return cartRepository.findByUserId(userId);
    }

    public Cart addItemToCart(int userId, ItemCartRequest item) {
        if (item.quantity() < 1) {
            throw new QuantityLessThanOneException();
        }

        Cart cart = cartRepository.findByUserId(userId);

        CartDetails savedCartDetails = cartDetailRepository.findByCartIdAndItemId(cart.getId(), item.itemId());
        if (savedCartDetails != null) {
            savedCartDetails.setQuantity(savedCartDetails.getQuantity() + item.quantity());
            cartDetailRepository.save(savedCartDetails);
            return cart;
        }

        CartDetails cartDetails = new CartDetails();
        Item itemToAdd = new Item();
        itemToAdd.setId(item.itemId());
        cartDetails.setCart(cart);
        cartDetails.setQuantity(item.quantity());
        cartDetails.setItem(itemToAdd);
        cartDetailRepository.save(cartDetails);
        return cart;
    }

    public ResponseEntity<String> updateItemInCart(int userId, ItemCartRequest itemCartRequest) {
        if (itemCartRequest.quantity() < 1) {
            throw new QuantityLessThanOneException();
        }

        Cart cart = cartRepository.findByUserId(userId);
        CartDetails savedCartDetails = cartDetailRepository.findByCartIdAndItemId(cart.getId(), itemCartRequest.itemId());

        if (savedCartDetails == null) {
            throw new ItemNotFoundException();
        }

        savedCartDetails.setQuantity(itemCartRequest.quantity());
        cartDetailRepository.save(savedCartDetails);

        return ResponseEntity.noContent().build();

    }

    public ResponseEntity<String> deleteItemFromCart(int userId, int cartDetailsId) {
        Cart cart = cartRepository.findByUserId(userId);
        CartDetails cartDetails = cartDetailRepository.findByCartIdAndId(cart.getId(), cartDetailsId).orElseThrow(CartDetailsNotFoundException::new);
        cartDetailRepository.delete(cartDetails);

        return ResponseEntity.ok().build();
    }

}
