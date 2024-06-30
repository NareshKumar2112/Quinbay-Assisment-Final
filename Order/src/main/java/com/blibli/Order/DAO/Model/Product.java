package com.blibli.Order.DAO.Model;

import lombok.Data;


@Data
public class Product {

    private long id;
    private String name;
    private double cost;
    private int quantity;
    private long seller_id;
    private long category_id;

}

