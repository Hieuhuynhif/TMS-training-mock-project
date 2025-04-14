package com.example.tmstraining.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_details")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Max(100)
    @Min(0)
    @NotNull
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    public static List<OrderDetails> toListOrderDetail(List<CartDetails> listCartDetails, Order order) {

        return listCartDetails.stream()
                .map(cartDetails -> {
                            OrderDetails orderDetails = new OrderDetails();
                            orderDetails.setItem(cartDetails.getItem());
                            orderDetails.setQuantity(cartDetails.getQuantity());
                            orderDetails.setOrder(order);
                            return orderDetails;
                        }
                ).toList();
    }

}
