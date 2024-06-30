package com.blibli.inventory.Service.serviceInterface;

import com.blibli.inventory.DAO.Model.*;
import com.blibli.inventory.Dto.*;

import java.util.*;

public interface SellerServiceInterface {

    public String addSeller(Seller seller);

    public List<SellerDto> getSeller();

    public Seller getSellerById(long id);


}
