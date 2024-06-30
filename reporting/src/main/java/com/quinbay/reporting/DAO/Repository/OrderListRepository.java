package com.quinbay.reporting.DAO.Repository;

import com.quinbay.reporting.DAO.Model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface OrderListRepository extends JpaRepository<OrderList,Long> {


}
