package com.blibli.Order.Controller;

import com.blibli.Order.Dto.*;
import com.blibli.Order.Exceptions.*;
import com.blibli.Order.DAO.Model.Product;
import com.blibli.Order.Service.ServiceImplementation.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService order_service;

    @GetMapping("/get")
    public ResponseEntity<?> getOrders() {
        List<OrdersDto> allOrders=order_service.getOrders();
        if(allOrders.isEmpty())
        {
            return new ResponseEntity<>("no orders",HttpStatus.OK);
        }
        return new ResponseEntity<>(allOrders,HttpStatus.OK);
    }

    @GetMapping("/get/products")
    public List<Product> getAllProducts() throws ProductNotFoundException
    {
        List<Product> allProduct=order_service.getAllProducts();
        return allProduct;
    }
    @PostMapping("/confirm/{email}")
    public String confirmOrder(@PathVariable String email)
    {
        return order_service.confirmOrder(email);
    }
}