package com.quinbay.reporting.DAO.Model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "orderList")
public class OrderList {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private long id;
    private String productName;
    private double productCost;
    private int productQuantity;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "order_id")
    @JsonBackReference("orders")
    private Orders orders;

}
