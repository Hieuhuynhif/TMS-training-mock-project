package com.example.tmstraining.controller;

import com.example.tmstraining.entities.Order;
import com.example.tmstraining.security.CustomUserDetails;
import com.example.tmstraining.service.OrderService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public List<Order> findAllOrders(@AuthenticationPrincipal CustomUserDetails userDetails) {
        int customerId = userDetails.getId();
        return orderService.getAllOrders(customerId);
    }

    @GetMapping("{customerId}")
    public List<Order> findAllOrders(@PathVariable int customerId) {
        return orderService.getAllOrders(customerId);
    }

    @PostMapping("")
    public Order createOrder(@AuthenticationPrincipal CustomUserDetails userDetails) {
        int customerId = userDetails.getId();
        return orderService.createOrder(customerId);
    }

    @GetMapping("lastestOrder/{customerId}")
    public Order findLastestOrder(@PathVariable int customerId) {
        return orderService.findLastestOrder(customerId);
    }

    @GetMapping("lastestOrder")
    public Order findLastestOrder(@AuthenticationPrincipal CustomUserDetails userDetails) {
        int customerId = userDetails.getId();
        return orderService.findLastestOrder(customerId);
    }

}
