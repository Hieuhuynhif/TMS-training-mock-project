package com.example.tmstraining.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "cart_details")
public class CartDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Min(0)
    @Max(100)
    private int quantity;

    @NotNull
    private Date AddedDate = new Date();

    @ManyToOne()
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne()
    @JoinColumn(name = "cart_id")
    @JsonBackReference
    private Cart cart;
}
