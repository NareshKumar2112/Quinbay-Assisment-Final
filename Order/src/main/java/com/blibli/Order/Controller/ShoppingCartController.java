package com.blibli.Order.Controller;

import com.blibli.Order.DAO.Model.ShoppingCart;
import com.blibli.Order.Service.ServiceImplementation.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService cartService;

    @PostMapping("/add")
    public String addToCart(@RequestBody ShoppingCart shoppingCart)
    {
        return cartService.addToCart(shoppingCart);
    }
    @DeleteMapping("/delete/{email}")
    public String deleteCart(@PathVariable String email)
    {
        return cartService.deleteCart(email);
    }
    @GetMapping("/get/{email}")
    public ResponseEntity<?> getShppingCart(@PathVariable String email)
    {
        return cartService.getShoppingCartById(email);
    }
    @PutMapping("/update")
    public String updateCart(@RequestBody ShoppingCart shoppingCart)
    {
        return cartService.updateCart(shoppingCart);
    }
}
