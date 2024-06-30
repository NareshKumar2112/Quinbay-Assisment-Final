package com.blibli.inventory.DAO.Model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Data
@Entity
@Table(name="Seller")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seller_id;
    private String sellerName;
    private String phoneNumber;

    @OneToMany(mappedBy = "seller")
    @JsonManagedReference("seller")
    private List<Product> productList=new ArrayList<>();
}
