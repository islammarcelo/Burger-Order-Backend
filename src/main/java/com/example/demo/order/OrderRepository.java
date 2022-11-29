package com.example.demo.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<CustomerOrder, Integer> {
    @Query("SELECT co from CustomerOrder co JOIN BurgerUser bu ON bu.id = :id")
    List<CustomerOrder> getOrdersByUserId(int id);
}
