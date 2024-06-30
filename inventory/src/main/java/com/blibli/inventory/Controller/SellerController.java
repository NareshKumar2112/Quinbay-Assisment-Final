package com.blibli.inventory.Controller;

import com.blibli.inventory.DAO.Model.*;
import com.blibli.inventory.Dto.*;
import com.blibli.inventory.Service.ServiceImplementation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PostMapping("/add")
    public String addSeller(@RequestBody Seller seller)
    {
        return sellerService.addSeller(seller);
    }
    @GetMapping("/get")
    public List<SellerDto> getSeller()
    {
        return sellerService.getSeller();
    }
}
