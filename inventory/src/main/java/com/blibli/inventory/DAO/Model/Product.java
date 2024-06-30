package com.blibli.inventory.DAO.Model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "product")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double cost;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference("category")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    @JsonBackReference("seller")
    private Seller seller;
}