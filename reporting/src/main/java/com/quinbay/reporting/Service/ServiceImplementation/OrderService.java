package com.quinbay.reporting.Service.ServiceImplementation;

import com.quinbay.reporting.DAO.Model.*;
import com.quinbay.reporting.DAO.Repository.*;
import com.quinbay.reporting.DTO.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.annotation.*;
import org.springframework.kafka.core.*;
import org.springframework.stereotype.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.*;
import java.time.*;
import java.util.*;

@Service
public class OrderService {


    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private OrderListRepository orderListRepository;

    @Autowired
    private OrderRepository orderRepository;

    @KafkaListener(topics = "ordermessage",groupId = "order-group",containerFactory = "kafkaListenerContainerFactory")
    public void getOrderData(Order order)
    {
        System.out.println("sfs");
        System.out.println(order.getUserName()+" "+order.getProductName());
        Orders orders=new Orders();
        orders.setDate(LocalDate.now()+"");
        System.out.println(order.getUserName()+" "+order.getProductName());
        orders.setUserName(order.getUserName());
        orders.setTotalAmount(order.getTotalAmount());
        Orders orders1=orderRepository.save(orders);
        System.out.println(order.getUserName()+" "+order.getProductName());
        for(int i=0;i<order.getProductName().size();i++)
        {
            OrderList orderList=new OrderList();
            System.out.println(order.getUserName()+" "+order.getProductName());
            orderList.setProductCost(order.getProductCost().get(i));
            orderList.setProductName(order.getProductName().get(i));
            orderList.setProductQuantity(order.getProductQuantity().get(i));
            orderList.setOrders(orders1);
            OrderList orderList1=orderListRepository.save(orderList);
        }
        System.out.println(order.getUserName()+" "+order.getProductName());
        String email=order.getEmail();

        kafkaTemplate.send("confirm-message",email);
        try {
            postDataInExcel();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
        System.out.println("done successfully");
    }

    public void postDataInExcel() throws IOException
    {
        List<Orders> orderList=orderRepository.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("orderData");

        int rowCount = 0;

        for (Orders orders : orderList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            Cell idCell = row.createCell(columnCount++);
            idCell.setCellValue(orders.getId());
            Cell userNameCell = row.createCell(columnCount++);
            userNameCell.setCellValue(orders.getUserName());
            Cell amountCell = row.createCell(columnCount++);
            amountCell.setCellValue(orders.getTotalAmount());
            Cell dateCell = row.createCell(columnCount++);
            dateCell.setCellValue(orders.getDate());
            System.out.println("sf");
        }
        Path desktopPath = Paths.get(System.getProperty("user.home"), "Desktop", "orderData.xlsx");
        try (FileOutputStream outputStream = new FileOutputStream(desktopPath.toFile())) {
            workbook.write(outputStream);
        }

        workbook.close();
    }
    public String sample()
    {
        kafkaTemplate.send("confirm-message","727721euit099@skcet.ac.in");
        return "hi";
    }

    public List<Optional<OrderList>> filterByName(String userName) {
        List<Orders> orderList=orderRepository.findAll();
        List<Optional<OrderList>> resultantList=new ArrayList<>();
        for(int i=0;i<orderList.size();i++)
        {
            if(orderList.get(i).getUserName().equals(userName))
            {
                Optional<OrderList> orderList1=orderListRepository.findById(orderList.get(i).getId());
                resultantList.add(orderList1);
            }
        }
        return resultantList;
    }

    public List<Optional<OrderList>> filterById(long id) {
        List<Orders> orderList=orderRepository.findAll();
        List<Optional<OrderList>> resultantList=new ArrayList<>();
        for(int i=0;i<orderList.size();i++)
        {
            if(orderList.get(i).getId()==id)
            {
                Optional<OrderList> orderList1=orderListRepository.findById(orderList.get(i).getId());
                resultantList.add(orderList1);
            }
        }
        return resultantList;
    }

    public List<Optional<OrderList>> filterByDate(String fromDate, String toDate) {
        List<Orders> orderList=orderRepository.findByDate(fromDate,toDate);
        List<Optional<OrderList>> resultantList=new ArrayList<>();
        for(int i=0;i<orderList.size();i++)
        {
                Optional<OrderList> orderList1=orderListRepository.findById(orderList.get(i).getId());
                resultantList.add(orderList1);
        }
        return resultantList;
    }

    public List<OrderDto> getAll() {
        List<OrderList> orderList=orderListRepository.findAll();
        List<OrderDto> resultantList=new ArrayList<>();
        for(int i=0;i<orderList.size();i++)
        {
            Optional<Orders> orders=orderRepository.findById(orderList.get(i).getOrders().getId());
            if(orders!=null)
            {
                Orders order=orders.get();
                OrderDto orderDto=new OrderDto();
                orderDto.setDate(order.getDate());
                orderDto.setProductName(orderList.get(i).getProductName());
                orderDto.setQuantity(orderList.get(i).getProductQuantity());
                orderDto.setUserName(order.getUserName());
                resultantList.add(orderDto);
            }
        }
        return resultantList;
    }
}
