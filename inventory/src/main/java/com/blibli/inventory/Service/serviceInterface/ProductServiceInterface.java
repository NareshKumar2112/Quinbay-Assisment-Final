package com.blibli.inventory.Service.serviceInterface;

import com.blibli.inventory.DAO.Model.*;
import com.blibli.inventory.Dto.*;
import com.blibli.inventory.Exception.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

import java.util.*;

public interface ProductServiceInterface {

    public String addProduct(Product product)throws JsonMappingException, JsonProcessingException,CategoryNotFoundException;

    public List<ProductDto> getProducts() throws ProductNotFoundException;

    public String updateProduct(Product product)throws JsonMappingException, JsonProcessingException,ProductNotFoundException,CategoryNotFoundException;

    public String deleteProduct(long id);

    public Product getproductbyid(long id);

    public ProductDto getProductById(long id) throws ProductNotFoundException;

}
