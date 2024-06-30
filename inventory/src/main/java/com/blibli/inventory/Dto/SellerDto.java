package com.blibli.inventory.Dto;

import com.blibli.inventory.DAO.Model.*;
import lombok.*;

import java.util.*;

@Data
public class SellerDto {

    private String name;
    private String phoneNumber;
    private List<Product> productList;
}
