package com.example.tmstraining.service;

import com.example.tmstraining.entities.Cart;
import com.example.tmstraining.entities.CartDetails;
import com.example.tmstraining.entities.Order;
import com.example.tmstraining.entities.OrderDetails;
import com.example.tmstraining.exception.CartDetailsNotFoundException;
import com.example.tmstraining.exception.NotFoundException;
import com.example.tmstraining.repository.CartDetailsRepository;
import com.example.tmstraining.repository.OrderDetailsRepository;
import com.example.tmstraining.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private CartService cartService;
    @Mock
    private OrderDetailsRepository orderDetailsRepository;
    @Mock
    private CartDetailsRepository cartDetailsRepository;

    @Test
    public void getAllOrders() {
        int customerId = 1;
        List<Order> mockOrder = List.of(new Order());
        Mockito.when(orderRepository.findAllByUserId(customerId)).thenReturn(mockOrder);
        List<Order> result = orderService.getAllOrders(customerId);
        Assertions.assertEquals(mockOrder, result);
    }

    @Test
    public void createOrder() {
        int customerId2 = 2;
        Cart cart2 = new Cart();
        cart2.setListCartDetails(List.of());

        Mockito.when(cartService.findByUserId(customerId2)).thenReturn(cart2);
        Assertions.assertThrows(CartDetailsNotFoundException.class, () -> orderService.createOrder(customerId2));

        int customerId1 = 1;
        Order mockOrder = new Order();
        mockOrder.setId(1);
        Cart cart1 = new Cart();

        Mockito.when(cartService.findByUserId(customerId1)).thenReturn(cart1);
        List<CartDetails> mockListCartDetails = List.of(new CartDetails());
        cart1.setListCartDetails(mockListCartDetails);
        List<OrderDetails> mockListOrderDetails = List.of(new OrderDetails());
        Mockito.when(orderRepository.save(mockOrder)).thenReturn(mockOrder);
        Mockito.when(orderDetailsRepository.saveAll(mockListOrderDetails)).thenReturn(mockListOrderDetails);
        Mockito.doNothing().when(cartDetailsRepository).deleteAllByCartId(cart1.getId());

        Order result = orderService.createOrder(customerId1);

        Assertions.assertEquals(mockOrder, result);
    }

    @Test
    public void findLastestOrder() {
        int customerId1 = 1;
        int customerId2 = 2;
        Order mockOrder = new Order();
        mockOrder.setId(1);

        Mockito.when(orderRepository.findFirstByUserIdOrderByOrderDateDesc(customerId1)).thenReturn(Optional.of(mockOrder));
        Order result1 = orderService.findLastestOrder(customerId1);
        Assertions.assertEquals(mockOrder, result1);

        Mockito.when(orderRepository.findFirstByUserIdOrderByOrderDateDesc(customerId2)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> orderService.findLastestOrder(customerId2));
    }
}
