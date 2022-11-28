package com.example.demo.order;

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

    public CustomerOrder() {
    }

    public CustomerOrder(int id, String time, double totalCost) {
        this.id = id;
        this.time = time;
        this.totalCost = totalCost;
    }

    public CustomerOrder(String time, double totalCost) {
        this.time = time;
        this.totalCost = totalCost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return time;
    }

    public void setData(String time) {
        this.time = time;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", data=" + time +
                ", totalCost=" + totalCost +
                '}';
    }
}
