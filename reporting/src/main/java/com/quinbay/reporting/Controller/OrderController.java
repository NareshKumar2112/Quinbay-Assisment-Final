package com.quinbay.reporting.Controller;

import com.quinbay.reporting.DAO.Model.*;
import com.quinbay.reporting.DTO.*;
import com.quinbay.reporting.Service.ServiceImplementation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/reporting")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/get")
    public List<OrderDto> getAll()
    {
        return orderService.getAll();
    }
    @GetMapping("/filterByName/{userName}")
    public List<Optional<OrderList>> filterByName(@PathVariable String userName)
    {
        return orderService.filterByName(userName);
    }

    @GetMapping("/filterById/{id}")
    public List<Optional<OrderList>> filterById(@PathVariable long id)
    {
        return orderService.filterById(id);
    }

    @GetMapping("/filterByDate/{fromDate}/{toDate}")
    public List<Optional<OrderList>> filterByDate(@PathVariable String fromDate,@PathVariable String toDate)
    {
        return orderService.filterByDate(fromDate,toDate);
    }
}
