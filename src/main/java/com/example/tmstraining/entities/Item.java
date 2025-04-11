package com.example.tmstraining.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 30)
    private String name;

    @NotNull
    @Max(1000 * 1000 * 1000)
    @Min(0)
    private double price;

    @OneToMany(mappedBy = "item")
    private List<OrderDetails> listOrderDetails;

    @OneToMany(mappedBy = "item")
    private List<CartDetails> listCartDetails;

}
