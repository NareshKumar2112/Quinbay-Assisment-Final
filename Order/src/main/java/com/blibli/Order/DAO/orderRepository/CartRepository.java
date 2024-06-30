package com.blibli.Order.DAO.orderRepository;

import com.blibli.Order.DAO.Model.ShoppingCart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends MongoRepository<ShoppingCart,String> {

}
