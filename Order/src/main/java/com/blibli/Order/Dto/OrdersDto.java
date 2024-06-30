package com.blibli.Order.Dto;

import com.blibli.Order.DAO.Model.*;

import lombok.*;

import java.util.*;

@Data
public class OrdersDto {

    private double totalAmount;
    private String userName;
    private String email;
    private List<String> productName;
    private List<Integer> productQuantity;
    private List<Double> productCost;

}
