package com.example.tmstraining.service;

import com.example.tmstraining.dtos.cart.ItemCartRequest;
import com.example.tmstraining.entities.Cart;
import com.example.tmstraining.entities.CartDetails;
import com.example.tmstraining.exception.CartDetailsNotFoundException;
import com.example.tmstraining.exception.ItemNotFoundException;
import com.example.tmstraining.exception.QuantityLessThanOneException;
import com.example.tmstraining.repository.CartDetailsRepository;
import com.example.tmstraining.repository.CartRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {
    @InjectMocks
    private CartService cartService;

    @Mock
    private CartRepository cartRepository;
    @Mock
    private CartDetailsRepository cartDetailsRepository;


    @Test
    public void findByUserId() {
        int userId = 99;
        Cart cart = new Cart();
        cart.setId(1);
        Mockito.when(cartRepository.findByUserId(userId)).thenReturn(cart);
        Assertions.assertEquals(cart, cartService.findByUserId(userId));
    }

    @Test
    public void addItemToCart() {
//        case 1
        int userId = 99;
        ItemCartRequest item1 = new ItemCartRequest(1, 30);
        Cart cart = new Cart();
        cart.setId(1);

        Mockito.when(cartRepository.findByUserId(userId)).thenReturn(cart);
        Mockito.when(cartDetailsRepository.findByCartIdAndItemId(cart.getId(), item1.itemId())).thenReturn(null);

        Assertions.assertEquals(cart, cartService.addItemToCart(userId, item1));

//        case 2
        ItemCartRequest item2 = new ItemCartRequest(1, 0);
        Assertions.assertThrows(QuantityLessThanOneException.class, () -> cartService.addItemToCart(userId, item2));

    }

    @Test
    public void updateItemInCart() {
        int userId = 99;
        ItemCartRequest item1 = new ItemCartRequest(1, 30);
        Cart cart = new Cart();
        cart.setId(1);
        Mockito.when(cartRepository.findByUserId(userId)).thenReturn(cart);
        Mockito.when(cartDetailsRepository.findByCartIdAndItemId(cart.getId(), item1.itemId())).thenReturn(null);
        Assertions.assertThrows(ItemNotFoundException.class, () -> cartService.updateItemInCart(userId, item1));

        ItemCartRequest item2 = new ItemCartRequest(1, 0);
        Assertions.assertThrows(QuantityLessThanOneException.class, () -> cartService.updateItemInCart(userId, item2));

        ItemCartRequest item3 = new ItemCartRequest(1, 30);
        Mockito.when(cartDetailsRepository.findByCartIdAndItemId(cart.getId(), item3.itemId())).thenReturn(new CartDetails());
        Assertions.assertEquals(ResponseEntity.noContent().build(), cartService.updateItemInCart(userId, item3));

    }

    @Test
    public void deleteItemFromCart() {
        int userId = 99;
        int cartDetailsId1 = 22;
        int cartDetailsId2 = 33;
        Cart cart = new Cart();
        cart.setId(1);
        Mockito.when(cartRepository.findByUserId(userId)).thenReturn(cart);
        Mockito.when(cartDetailsRepository.findByCartIdAndId(cart.getId(), cartDetailsId1)).thenReturn(Optional.empty());
        Assertions.assertThrows(CartDetailsNotFoundException.class, () -> cartService.deleteItemFromCart(userId, cartDetailsId1));

        Mockito.when(cartDetailsRepository.findByCartIdAndId(cart.getId(), cartDetailsId2)).thenReturn(Optional.of(new CartDetails()));
        Assertions.assertEquals(ResponseEntity.ok().build(), cartService.deleteItemFromCart(userId, cartDetailsId2));
    }
}
