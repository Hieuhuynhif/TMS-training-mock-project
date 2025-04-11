package com.example.tmstraining.controllers;

import com.example.tmstraining.entities.Cart;
import com.example.tmstraining.entities.CartDetails;
import com.example.tmstraining.entities.Item;
import com.example.tmstraining.entities.User;
import com.example.tmstraining.services.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/carts")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{customerId}")
    public Cart findCartByCustomerId(@PathVariable int customerId) {
        return cartService.findByUserId(customerId);
    }

    @PostMapping("")
    public Item addItemToCart(Authentication authentication, @RequestBody CartDetails cartDetails) {
        User user = (User) authentication.getPrincipal();
        return cartService.addItemToCart(user.getId(), cartDetails);
    }

    @PutMapping("")
    public ResponseEntity<String> updateCart(@RequestBody CartDetails cartDetails, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return cartService.updateItemInCart(user.getId(), cartDetails);
    }

    @DeleteMapping("{cartDetailsId}")
    public ResponseEntity<String> deleteCartDetails(@PathVariable int cartDetailsId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return cartService.deleteItemFromCart(user.getId(), cartDetailsId);
    }
}
