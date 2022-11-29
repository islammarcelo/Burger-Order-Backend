package com.example.demo.order;

import com.example.demo.user.BurgerUser;

import javax.persistence.*;
@Entity
@Table
public class CustomerOrder {

    @Id
    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence"
    )

    private int id;
    private String time;
    private double totalCost;
    @ManyToOne
    private BurgerUser burgerUser;

    public CustomerOrder() {
    }

    public CustomerOrder(String time, double totalCost, BurgerUser burgerUser) {
        this.time = time;
        this.totalCost = totalCost;
        this.burgerUser = burgerUser;
    }

    public CustomerOrder(int id, String time, double totalCost, BurgerUser burgerUser) {
        this.id = id;
        this.time = time;
        this.totalCost = totalCost;
        this.burgerUser = burgerUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public BurgerUser getBurgerUser() {
        return burgerUser;
    }

    public void setBurgerUser(BurgerUser burgerUser) {
        this.burgerUser = burgerUser;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", time=" + time +
                ", totalCost=" + totalCost +
                '}';
    }
}
