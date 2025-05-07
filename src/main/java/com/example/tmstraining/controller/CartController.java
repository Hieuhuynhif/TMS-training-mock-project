package com.example.tmstraining.controller;

import com.example.tmstraining.dtos.cart.ItemCartRequest;
import com.example.tmstraining.entities.Cart;
import com.example.tmstraining.security.CustomUserDetails;
import com.example.tmstraining.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/carts")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("")
    public Cart findCartByCustomerId(@AuthenticationPrincipal CustomUserDetails userDetails) {
        int id = userDetails.getId();
        return cartService.findByUserId(id);
    }

    @GetMapping("/{customerId}")
    public Cart findCartByCustomerId(@PathVariable int customerId) {
        return cartService.findByUserId(customerId);
    }

    @PostMapping("")
    public Cart addItemToCart(@AuthenticationPrincipal CustomUserDetails userDetails,
                              @RequestBody ItemCartRequest item) {
        int userId = userDetails.getId();
        return cartService.addItemToCart(userId, item);
    }

    @PutMapping("")
    public ResponseEntity<String> updateCart(@RequestBody ItemCartRequest itemCartRequest,
                                             @AuthenticationPrincipal CustomUserDetails userDetails) {
        int userId = userDetails.getId();
        return cartService.updateItemInCart(userId, itemCartRequest);
    }

    @DeleteMapping("{cartDetailsId}")
    public ResponseEntity<String> deleteCartDetails(@PathVariable int cartDetailsId,
                                                    @AuthenticationPrincipal CustomUserDetails userDetails) {
        int userId = userDetails.getId();
        return cartService.deleteItemFromCart(userId, cartDetailsId);
    }
}
