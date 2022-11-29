package com.example.demo;

import com.example.demo.item.Item;
import com.example.demo.item.ItemRepository;

import com.example.demo.order.CustomerOrder;
import com.example.demo.order.OrderRepository;
import com.example.demo.user.BurgerUser;
import com.example.demo.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class ProjectConfig {

    @Bean
    CommandLineRunner commandLineRunnerUser(
            UserRepository userRepository,ItemRepository itemRepository, OrderRepository orderRepository){
        return args -> {
            BurgerUser user1 = new BurgerUser(

                    "islam",
                    "adel",
                    123456
            );
            BurgerUser user2 = new BurgerUser(
                    "mohamed",
                    "adel",
                    12345
            );
            Item item1 = new Item(
                    "beef burger",
                    60,
                    100
            );
            Item item2 = new Item(
                    "chicken burger",
                    55,
                    200
            );
            Set<Item> items =new HashSet<Item>();
            items.add(item1);
            items.add(item2);
            CustomerOrder order1 = new CustomerOrder(
                    "01-01-2020",
                    130.0,
                    user1


            );
            order1.setItems(items);
            CustomerOrder order2 = new CustomerOrder(
                    "01-01-2020",
                    190.0,
                    user2

            );
            order2.setItems(items);

            userRepository.saveAll(List.of(user1,user2));
            orderRepository.saveAll(List.of(order1,order2));
            itemRepository.saveAll(List.of(item1,item2));



        };
    };
}
