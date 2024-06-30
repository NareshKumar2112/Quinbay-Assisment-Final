package com.blibli.inventory.Dto;

import lombok.*;

@Data
public class ProductDto {

    private String name;
    private double cost;
    private int quantity;
    private long seller_id;
    private long category_id;

}
