package com.quinbay.reporting.DAO.Repository;

import com.quinbay.reporting.DAO.Model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.config.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {

    @Query(value="SELECT * FROM public.orders where date>=? and date<=?",nativeQuery = true)
    public List<Orders> findByDate(String fromDate,String toDate);

}
