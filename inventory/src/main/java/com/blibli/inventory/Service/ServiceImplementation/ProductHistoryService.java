package com.blibli.inventory.Service.ServiceImplementation;

import com.blibli.inventory.DAO.Model.*;
import com.blibli.inventory.DAO.Repository.*;
import com.blibli.inventory.Service.serviceInterface.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;
@Service
public class ProductHistoryService implements ProductHistoryServiceInterface {

    @Autowired
    private ProductHistoryRepository productHistoryRepository;

    public List<ProductHistory> getProductHistory() {
        return productHistoryRepository.findAll();
    }
}
