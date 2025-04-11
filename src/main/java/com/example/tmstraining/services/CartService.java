package com.example.tmstraining.services;

import com.example.tmstraining.entities.Cart;
import com.example.tmstraining.entities.CartDetails;
import com.example.tmstraining.entities.Item;
import com.example.tmstraining.exceptions.CartDetailsNotFoundException;
import com.example.tmstraining.exceptions.ItemNotFoundException;
import com.example.tmstraining.exceptions.QuantityLessThanOneException;
import com.example.tmstraining.repositories.CartDetailsRepository;
import com.example.tmstraining.repositories.CartRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartDetailsRepository cartDetailRepository;
    private final ItemService itemService;

    public CartService(CartRepository cartRepository, CartDetailsRepository cartDetailRepository, ItemService itemService) {
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.itemService = itemService;
    }

    public Cart findByUserId(int userId) {
        return cartRepository.findByUserId(userId);
    }

    public Item addItemToCart(int userId, CartDetails cartDetails) {
        if (cartDetails.getQuantity() < 1) {
            throw new QuantityLessThanOneException();
        }

        Cart cart = cartRepository.findByUserId(userId);
        CartDetails savedCartDetails = cartDetailRepository.findByCartIdAndItemId(cart.getId(), cartDetails.getItem().getId());
        if (savedCartDetails != null) {
            savedCartDetails.setQuantity(savedCartDetails.getQuantity() + 1);
            cartDetailRepository.save(savedCartDetails);
            return itemService.findItemById(savedCartDetails.getItem().getId());
        }

        cartDetails.setCart(cart);
        cartDetailRepository.save(cartDetails);
        return itemService.findItemById(cartDetails.getItem().getId());
    }

    public ResponseEntity<String> updateItemInCart(int userId, CartDetails cartDetails) {
        if (cartDetails.getQuantity() < 1) {
            throw new QuantityLessThanOneException();
        }

        Cart cart = cartRepository.findByUserId(userId);
        CartDetails savedCartDetails = cartDetailRepository.findByCartIdAndItemId(cart.getId(), cartDetails.getItem().getId());

        if (savedCartDetails == null) {
            throw new ItemNotFoundException();
        }

        savedCartDetails.setQuantity(cartDetails.getQuantity());
        cartDetailRepository.save(savedCartDetails);

        return ResponseEntity.noContent().build();

    }

    public ResponseEntity<String> deleteItemFromCart(int userId, int cartDetailsId) {
        CartDetails cartDetails = cartDetailRepository.findById(cartDetailsId).orElseThrow(() -> new CartDetailsNotFoundException());

        cartDetailRepository.delete(cartDetails);

        return ResponseEntity.ok().build();

    }

}
