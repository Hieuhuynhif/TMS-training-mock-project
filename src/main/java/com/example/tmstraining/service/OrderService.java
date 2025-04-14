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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final CartDetailsRepository cartDetailsRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    public OrderService(OrderRepository orderRepository, CartService cartService, CartDetailsRepository cartDetailsRepository, OrderDetailsRepository orderDetailsRepository) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.cartDetailsRepository = cartDetailsRepository;

        this.orderDetailsRepository = orderDetailsRepository;
    }

    public List<Order> getAllOrders(int customerId) {
        return orderRepository.findAllByUserId(customerId);
    }

    @Transactional
    public Order createOrder(int customerId) {
        Cart cart = cartService.findByUserId(customerId);
        List<CartDetails> listCartDetails = cart.getListCartDetails();

        if (listCartDetails.isEmpty()) {
            throw new CartDetailsNotFoundException();
        }

        Order order = new Order();
        order.setUser(cart.getUser());
        Order newOrder = orderRepository.save(order);
        List<OrderDetails> listOrderDetails = OrderDetails.toListOrderDetail(listCartDetails, newOrder);
        orderDetailsRepository.saveAll(listOrderDetails);
        cartDetailsRepository.deleteAllByCartId(cart.getId());
        newOrder.setListOrderDetails(listOrderDetails);
        return newOrder;
    }

    public Order findLastestOrder(int customerId) {
        return orderRepository.findFirstByUserIdOrderByOrderDateDesc(customerId).orElseThrow(() -> new NotFoundException("You dont have any order"));
    }
}
