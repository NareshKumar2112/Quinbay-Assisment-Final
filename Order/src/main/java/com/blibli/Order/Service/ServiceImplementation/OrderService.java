package com.blibli.Order.Service.ServiceImplementation;

import com.blibli.Order.DAO.Model.*;
import com.blibli.Order.Dto.*;
import com.blibli.Order.Exceptions.*;

import com.blibli.Order.DAO.orderRepository.CartRepository;
import com.blibli.Order.DAO.orderRepository.OrdersRepository;
import com.blibli.Order.Service.serviceInterface.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.*;
import org.springframework.kafka.core.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class OrderService implements OrderServiceInterface {


    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    private static final String TOPIC = "ordermessage";


    public List<OrdersDto> getOrders()
    {
        List<Orders> ordersList=ordersRepository.findAll();
        List<OrdersDto> ordersDtoList=new ArrayList<>();
        for(int i=0;i<ordersList.size();i++)
        {
            OrdersDto dto=new OrdersDto();
            ShoppingCart shoppingCart1=ordersList.get(i).getShoppingCart();
            List<String> productName=new ArrayList<>();
            List<Integer> productQuantity=new ArrayList<>();
            List<Double> productCost=new ArrayList<>();

            for(int j=0;j<shoppingCart1.getProductList().size();j++)
            {
                productName.add(shoppingCart1.getProductList().get(j).getName());
                productCost.add(shoppingCart1.getProductList().get(j).getCost());
                productQuantity.add(shoppingCart1.getProductList().get(j).getQuantity());
            }
            dto.setProductName(productName);
            dto.setProductCost(productCost);
            dto.setProductQuantity(productQuantity);
            dto.setUserName(shoppingCart1.getUserName());
            dto.setEmail(shoppingCart1.getEmail());
            dto.setTotalAmount(ordersList.get(i).getTotalAmount());
            ordersDtoList.add(dto);
        }
        kafkaTemplate.send(TOPIC,ordersList);
        return ordersDtoList;
    }
    public List<Product> getAllProducts() throws ProductNotFoundException
    {
        Product[] productList=restTemplate.getForObject("http://localhost:8080/product/get",Product[].class);
        if(productList.length==0)
        {
            throw new ProductNotFoundException("No products available");
        }
        return Arrays.asList(productList);
    }
    public Product getProductById(int id)
    {
        Product product=restTemplate.getForObject("http://localhost:8080/product/get/"+id,Product.class);
        if(product!=null)
        {
            return product;
        }
        return null;
    }

    public String confirmOrder(String email) {
        Optional<ShoppingCart> usershoppingCart=cartRepository.findById(email);
        if(usershoppingCart.isEmpty())
        {
            return "the cart is empty";
        }
        ShoppingCart shoppingCart=usershoppingCart.get();
        Orders orders=new Orders();
        double totalAmount=0;
        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String>entity=new HttpEntity<String>(headers);
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("id",1);
        List<Product> orderedList=shoppingCart.getProductList();
        for(int i=0;i<orderedList.size();i++)
        {
            System.out.println("fvfvf");
            long productId=orderedList.get(i).getId();
            int quantity=orderedList.get(i).getQuantity();
            Product product=restTemplate.exchange("http://localhost:8080/product/get/"+productId, HttpMethod.GET, entity, Product.class,hashMap).getBody();
            if(product!=null)
            {
                if(quantity>=0 ) {
                    if(quantity<=product.getQuantity()) {
                        totalAmount = totalAmount + (quantity * product.getCost());
                        product.setQuantity(product.getQuantity()-quantity);
                        product.setId(productId);
                        System.out.println(product.getId()+product.getName()+" "+product.getQuantity()+" "+product.getSeller_id()+product.getCategory_id());
                        String parameter=product.getId()+" "+product.getQuantity()+" "+product.getCategory_id()+" "+product.getSeller_id();
                        restTemplate.put("http://localhost:8080/product/updateProduct/"+parameter, new HashMap<>());
                        System.out.println("fee");
                    }
                    else {
                        return product.getName()+" is out of stock";
                    }
                }
                else {
                    return "invalid input";
                }
            }
            else
            {
                return "product is not available";
            }
        }
        orders.setTotalAmount(totalAmount);
        orders.setShoppingCart(shoppingCart);
        ordersRepository.save(orders);
        OrdersDto dto=new OrdersDto();
        ShoppingCart shoppingCart1=orders.getShoppingCart();
        List<String> productName=new ArrayList<>();
        List<Integer> productQuantity=new ArrayList<>();
        List<Double> productCost=new ArrayList<>();
        for(int i=0;i<shoppingCart1.getProductList().size();i++)
        {
            productName.add(shoppingCart1.getProductList().get(i).getName());
            productCost.add(shoppingCart1.getProductList().get(i).getCost());
            productQuantity.add(shoppingCart1.getProductList().get(i).getQuantity());
        }
        dto.setProductName(productName);
        dto.setProductCost(productCost);
        dto.setProductQuantity(productQuantity);
        dto.setUserName(shoppingCart1.getUserName());
        dto.setEmail(shoppingCart1.getEmail());
        dto.setTotalAmount(orders.getTotalAmount());
        System.out.println("cdd");
//        kafkaTemplate.send("abcd","sdsd");
        kafkaTemplate.send(TOPIC,dto);
        cartRepository.deleteById(email);
        return"order is placed successfully";
    }
}