package com.blibli.inventory.DAO.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Data
@Entity
@Table(name = "productHistory")
public class ProductHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long historyId;
    private String columnName;
    private String oldValue;
    private String newValue;
    private long productId;
    private String date;

}
