package com.example.tmstraining.controller;

import com.example.tmstraining.entities.Order;
import com.example.tmstraining.enums.Role;
import com.example.tmstraining.security.CustomUserDetails;
import com.example.tmstraining.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    OrderService orderService;

    @Test
    public void findAllOrders() {
        CustomUserDetails userDetails = new CustomUserDetails(1, "username", "password", Role.ROLE_CUSTOMER);
        int customerId = userDetails.getId();

        List<Order> mockListOrder = List.of(new Order());
        Mockito.when(orderService.getAllOrders(customerId)).thenReturn(mockListOrder);
        List<Order> result = orderController.findAllOrders(customerId);

        Assertions.assertEquals(mockListOrder, result);
    }


    @Test
    public void createOrder() {
        CustomUserDetails userDetails = new CustomUserDetails(1, "username", "password", Role.ROLE_CUSTOMER);
        int customerId = userDetails.getId();
        Order mockOrder = new Order();
        mockOrder.setId(1);

        Mockito.when(orderService.createOrder(customerId)).thenReturn(mockOrder);
        Order result = orderController.createOrder(userDetails);

        Assertions.assertEquals(mockOrder, result);
    }


    @Test
    public void findLastestOrder() {
        CustomUserDetails userDetails = new CustomUserDetails(1, "username", "password", Role.ROLE_CUSTOMER);
        int customerId = userDetails.getId();
        Order mockOrder = new Order();
        mockOrder.setId(1);
        Mockito.when(orderService.findLastestOrder(customerId)).thenReturn(mockOrder);
        Order result = orderController.findLastestOrder(userDetails);

        Assertions.assertEquals(mockOrder, result);
    }
}
