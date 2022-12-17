package com.example.demo.order;

import com.example.demo.item.Item;
import com.example.demo.user.BurgerUser;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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


    @ManyToMany(cascade = {
            CascadeType.MERGE
    })
    @JoinTable(
            name = "order_items",
            joinColumns = {
                    @JoinColumn(name = "item_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "order_id")
            }
    )
    private Set<Item> items = new HashSet<>();

    public CustomerOrder() {
    }

    public CustomerOrder(String time, double totalCost) {
        this.time = time;
        this.totalCost = totalCost;


    }

    public CustomerOrder(String time, double totalCost, BurgerUser burgerUser, Set<Item> items) {
        this.time = time;
        this.totalCost = totalCost;
        this.burgerUser = burgerUser;
        this.items = items;
    }

    public CustomerOrder(int id, String time, double totalCost) {
        this.id = id;
        this.time = time;
        this.totalCost = totalCost;


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

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
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
