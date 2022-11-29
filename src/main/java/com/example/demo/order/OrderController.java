package com.example.demo.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/order")
public class OrderController {

    final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<CustomerOrder> getOrders(){
        return orderService.getOrders();
    }

    @PostMapping
    public void addOrder(@RequestBody CustomerOrder customerOrder){
        orderService.AddOrder(customerOrder);
    }

    @DeleteMapping(path = "{orderId}")
    public void deleteOrder(@PathVariable("orderId")int orderId){
        orderService.deleteOrder(orderId);
    }

    @GetMapping(path = "{userId}")
    public List<CustomerOrder> getOrdersByUserId(@PathVariable("userId") int userId){
       return orderService.getOrdersByUserId(userId);
    }


}
