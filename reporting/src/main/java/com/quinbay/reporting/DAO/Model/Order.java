package com.quinbay.reporting.DAO.Model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Data
public class Order {

    private double totalAmount;
    private String userName;
    private String email;
    private List<String> productName;
    private List<Integer> productQuantity;
    private List<Double> productCost;

//    private double totalAmount;
//    private String userName;
//    private List<String> productName;
//    private List<Integer> productQuantity;
//    private List<Double> productCost;

    @JsonCreator
    public Order(
                    @JsonProperty("totalAmount") double totalAmount,
                    @JsonProperty("userName") String userName,
                    @JsonProperty("email") String email,
                    @JsonProperty("productName") List<String> productName,
                    @JsonProperty("productQuantity") List<Integer> productQuantity,
                    @JsonProperty("productCost") List<Double> productCost
    ) {

        this.totalAmount=totalAmount;
        this.userName=userName;
        this.email=email;
        this.productName=productName;
        this.productCost=productCost;
        this.productQuantity=productQuantity;
    }
}
