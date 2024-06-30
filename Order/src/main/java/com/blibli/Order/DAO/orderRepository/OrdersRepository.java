package com.blibli.Order.DAO.orderRepository;

import com.blibli.Order.DAO.Model.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrdersRepository extends MongoRepository<Orders,String> {
}
