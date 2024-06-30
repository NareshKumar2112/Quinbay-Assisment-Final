package com.blibli.inventory.Controller;

import com.blibli.inventory.DAO.Model.*;
import com.blibli.inventory.Service.ServiceImplementation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/productHistory")
public class ProductHistoryController {
    @Autowired
    private ProductHistoryService productHistoryService;

    @GetMapping("/get")
    public List<ProductHistory> getProductHistory()
    {
        return productHistoryService.getProductHistory();
    }
}
