package com.quinbay.reporting.DTO;

import lombok.*;

@Data
public class OrderDto {

    private String userName;
    private String productName;
    private String date;
    private int quantity;
}
