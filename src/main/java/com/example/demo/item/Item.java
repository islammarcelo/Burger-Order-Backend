package com.example.demo.item;

import com.example.demo.order.CustomerOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Item {

    @Id
    @SequenceGenerator(
            name = "item_sequence",
            sequenceName = "item_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_sequence"
    )
    private int id;
    private String name;
    private double price;
    private int code;

    @JsonIgnore
    @ManyToMany(mappedBy = "items")
    Set<CustomerOrder> orders = new HashSet<CustomerOrder>();

    public Item() {
    }

    public Item(int id, String name, double price, int code) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.code = code;

    }

    public Item(String name, double price, int code) {
        this.name = name;
        this.price = price;
        this.code = code;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Set<CustomerOrder> getOrders() {
        return orders;
    }

    public void setOrders(Set<CustomerOrder> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", code=" + code +
                '}';
    }
}

