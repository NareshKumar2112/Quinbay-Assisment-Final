package com.blibli.inventory.DAO.Repository;

import com.blibli.inventory.DAO.Model.*;
import org.springframework.data.jpa.repository.*;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
