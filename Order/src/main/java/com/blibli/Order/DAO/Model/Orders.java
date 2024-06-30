package com.blibli.Order.DAO.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "placedOrders")
public class Orders {

    @Id
    private String id;
    private ShoppingCart shoppingCart;
    private double totalAmount;

}
