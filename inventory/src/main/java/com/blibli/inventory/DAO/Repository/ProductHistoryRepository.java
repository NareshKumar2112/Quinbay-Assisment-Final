package com.blibli.inventory.DAO.Repository;

import com.blibli.inventory.DAO.Model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface ProductHistoryRepository extends JpaRepository<ProductHistory,Integer> {
}
