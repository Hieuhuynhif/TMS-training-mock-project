package com.example.tmstraining.controller;

import com.example.tmstraining.dtos.cart.ItemCartRequest;
import com.example.tmstraining.entities.Cart;
import com.example.tmstraining.enums.Role;
import com.example.tmstraining.security.CustomUserDetails;
import com.example.tmstraining.service.CartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class CartControllerTest {
    @InjectMocks
    CartController cartController;

    @Mock
    CartService cartService;

    @Test
    public void findCartByCustomerId() {
        Cart cart = new Cart();
        cart.setId(99);
        int userId = 1;

        Mockito.when(cartService.findByUserId(userId)).thenReturn(cart);
        Assertions.assertEquals(cart, cartController.findCartByCustomerId(1));
    }

    @Test
    public void addItemToCart() {
        CustomUserDetails userDetails = new CustomUserDetails(1, "username", "password", Role.ROLE_CUSTOMER);
        Cart cart = new Cart();
        cart.setId(1);
        ItemCartRequest item = new ItemCartRequest(1, 30);

        Mockito.when(cartService.addItemToCart(userDetails.getId(), item)).thenReturn(cart);
        Assertions.assertEquals(cart, cartController.addItemToCart(userDetails, item));
    }

    @Test
    public void updateCart() {
        CustomUserDetails userDetails = new CustomUserDetails(1, "username", "password", Role.ROLE_CUSTOMER);
        ItemCartRequest item = new ItemCartRequest(1, 20);
        Mockito.when(cartService.updateItemInCart(userDetails.getId(), item)).thenReturn(ResponseEntity.noContent().build());
        Assertions.assertEquals(ResponseEntity.noContent().build(), cartController.updateCart(item, userDetails));
    }

    @Test
    public void deleteCartDetails() {
        CustomUserDetails userDetails = new CustomUserDetails(1, "username", "password", Role.ROLE_CUSTOMER);
        int cartDetailsId = 1;
        Mockito.when(cartService.deleteItemFromCart(userDetails.getId(), cartDetailsId)).thenReturn(ResponseEntity.ok().build());
        Assertions.assertEquals(ResponseEntity.ok().build(), cartController.deleteCartDetails(cartDetailsId, userDetails));
    }
}
