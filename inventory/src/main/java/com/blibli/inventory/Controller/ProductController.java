package com.blibli.inventory.Controller;

import com.blibli.inventory.Dto.*;
import com.blibli.inventory.DAO.Model.Product;
import com.blibli.inventory.Exception.*;
import com.blibli.inventory.Service.ServiceImplementation.ProductService;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService product_service;

    @GetMapping("/get")
    public List<ProductDto> getProductName() throws ProductNotFoundException
    {
        List<ProductDto>productList=product_service.getProducts();
        return productList;
    }
    @PostMapping("/post")
    public String postProduct(@RequestBody Product product)throws JsonMappingException, JsonProcessingException,CategoryNotFoundException
    {
        return product_service.addProduct(product);
    }
    @PutMapping("/update")
    public String updateProduct(@RequestBody Product product)throws JsonMappingException, JsonProcessingException,ProductNotFoundException,CategoryNotFoundException
    {
        return product_service.updateProduct(product);
    }

    @PutMapping("/updateProduct/{parameter}")
    public String updateProductFromOrders(@PathVariable String parameter)throws JsonMappingException, JsonProcessingException,ProductNotFoundException,CategoryNotFoundException
    {
        String arr[]=parameter.split(" ");
        long productId=Long.parseLong(arr[0]);
        int quantity=Integer.parseInt(arr[1]);
        long category_id=Long.parseLong(arr[2]);
        long seller_id=Long.parseLong(arr[3]);
        return product_service.updateProductFromOrders(productId,quantity,category_id,seller_id);
    }
    @GetMapping("/kget")
    public String getKafkaData(@RequestParam String message) {
        product_service.sendMessage(message);
        return "Message sent to Kafka successfully!";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable long id)
    {
            return product_service.deleteProduct(id);
    }

    @PostMapping("/redis")
    public String getredis(@RequestParam String key,@RequestParam String value)
    {
        System.out.println("ffe");
        return  product_service.rediscache(key,value);
    }

    @GetMapping("/get/{id}")
    public ProductDto getProductById(@PathVariable long id)  throws ProductNotFoundException
    {
        return product_service.getProductById(id);
//        if(optionalProduct==null)
//        {
//            return null;
//        }
//        return optionalProduct;
    }
}