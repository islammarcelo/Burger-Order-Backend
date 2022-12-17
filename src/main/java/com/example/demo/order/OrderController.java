package com.example.demo.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class OrderController {

    final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/getOrders")
    public List<CustomerOrder> getOrders(){
        return orderService.getOrders();
    }

    @PostMapping("/addOrder")
    public void addOrder(@RequestBody CustomerOrder customerOrder){
        orderService.AddOrder(customerOrder);
    }

    @DeleteMapping(path = "/deleteOrder/{orderId}")
    public void deleteOrder(@PathVariable("orderId")int orderId){
        orderService.deleteOrder(orderId);
    }

    @GetMapping(path = "/getUserOrder{userId}")
    public List<CustomerOrder> getOrdersByUserId(@PathVariable("userId") int userId){
       return orderService.getOrdersByUserId(userId);
    }


}
