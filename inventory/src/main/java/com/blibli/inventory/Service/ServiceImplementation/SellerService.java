package com.blibli.inventory.Service.ServiceImplementation;

import com.blibli.inventory.DAO.Model.*;
import com.blibli.inventory.DAO.Repository.*;
import com.blibli.inventory.Dto.*;
import com.blibli.inventory.Service.serviceInterface.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class SellerService implements SellerServiceInterface {

    @Autowired
    private SellerRepository sellerRepository;

    public String addSeller(Seller seller)
    {
        if(sellerRepository.save(seller)==null)
        {
            return "failed";
        }
        return "added successfully";
    }
    public List<SellerDto> getSeller()
    {
        List<Seller> sellerList=sellerRepository.findAll();
        List<SellerDto>sellerDtoList=new ArrayList<>();
        for(int i=0;i<sellerList.size();i++)
        {
            SellerDto sellerDto=new SellerDto();
            sellerDto.setName(sellerList.get(i).getSellerName());
            sellerDto.setPhoneNumber(sellerList.get(i).getPhoneNumber());
            sellerDto.setProductList(sellerList.get(i).getProductList());
            sellerDtoList.add(sellerDto);
        }
        return sellerDtoList;
    }
    public Seller getSellerById(long id)
    {
        List<Seller> sellerList=sellerRepository.findAll();
        for(int i=0;i<sellerList.size();i++)
        {
            Seller seller=sellerList.get(i);
            if(id==seller.getSeller_id())
            {
                return seller;
            }
        }
        return null;
    }
}
