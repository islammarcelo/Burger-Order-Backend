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


import java.util.List;

@Configuration
public class ProjectConfig {

    @Bean
    CommandLineRunner commandLineRunnerUser(
            UserRepository userRepository){
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
            userRepository.saveAll(List.of(user1,user2));
        };
    };
    @Bean
    CommandLineRunner commandLineRunnerItem (ItemRepository itemRepository){
        return args -> {
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
            itemRepository.saveAll(List.of(item1,item2));
        };

    }

    @Bean
    CommandLineRunner commandLineRunnerOrder (OrderRepository orderRepository){
        return args -> {
            CustomerOrder order1 = new CustomerOrder(
                    "01-01-2020",
                    130.0
            );
            CustomerOrder order2 = new CustomerOrder(
                    "01-01-2020",
                    190.0
            );
            orderRepository.saveAll(List.of(order1,order2));
        };

    }

}
