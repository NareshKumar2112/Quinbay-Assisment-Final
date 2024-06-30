package com.quinbay.reporting.DAO.Model;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Data
@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private long id;
    private double totalAmount;
    private String userName;
    private String date;
    @OneToMany(mappedBy = "orders", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonManagedReference("orders")
    private List<OrderList> productList=new ArrayList<>();
}
