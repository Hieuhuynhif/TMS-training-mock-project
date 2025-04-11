package com.example.tmstraining.controllers;

import com.example.tmstraining.entities.Order;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    @GetMapping("{customerId}")
    public String findAllOrders(@PathVariable String customerId) {
        return "All orders data";
    }

    @PostMapping("")
    public String addOrder(@RequestBody Order order) {
        return "Order added";
    }

    @PutMapping("lastestOrder/{customerId")
    public String findLastestOrder(@PathVariable String customerId) {
        return "Lastest order found";
    }


}
