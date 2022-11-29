package com.example.demo.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    final OrderRepository orderRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<CustomerOrder> getOrders(){
        return orderRepository.findAll();
    }

    public void AddOrder(CustomerOrder customerOrder){
        orderRepository.save(customerOrder);
    }

    public void deleteOrder(int orderId) {
        boolean exists = orderRepository.existsById(orderId);
        if (!exists){
            throw  new IllegalStateException("Order with this Id "+orderId+" not exists");
        }
        orderRepository.deleteById(orderId);
    }


    public List<CustomerOrder> getOrdersByUserId(int userId) {
        return orderRepository.getOrdersByUserId(userId);
    }
}
