package com.blibli.inventory.DAO.Model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.io.*;
import java.util.*;

@Entity
@Data
@Table(name = "Category")
public class Category implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long category_id;
    private String name;
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    @JsonManagedReference("category")
    private List<Product> productList=new ArrayList<>();
}
