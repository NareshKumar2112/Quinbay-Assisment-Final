package com.blibli.Order.Service.serviceInterface;

import com.blibli.Order.DAO.Model.*;
import org.springframework.http.*;

public interface ShoppingCartServiceInterface {

    public String addToCart(ShoppingCart shoppingCart);

    public String deleteCart(String email);

    public ResponseEntity<?> getShoppingCartById(String email);

    public String updateCart(ShoppingCart shoppingCart);

}
