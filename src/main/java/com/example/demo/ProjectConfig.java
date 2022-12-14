package com.example.demo;

import com.example.demo.item.Item;
import com.example.demo.item.ItemRepository;

import com.example.demo.item.ItemService;
import com.example.demo.order.CustomerOrder;
import com.example.demo.order.OrderRepository;
import com.example.demo.user.BurgerUser;
import com.example.demo.user.Role;
import com.example.demo.user.UserRepository;
import com.example.demo.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class ProjectConfig {

    @Bean
    CommandLineRunner commandLineRunnerUser(
            UserService userService, ItemService itemService){
        return args -> {
            userService.saveRole(new Role("user"));
            userService.saveRole(new Role("admin"));

            userService.registerNewUser(new BurgerUser("islam","adel",12345,new ArrayList<>()));
            userService.registerNewUser(new BurgerUser("hamada","adel",123456,new ArrayList<>()));
            userService.registerNewUser(new BurgerUser("gehad","adel",123457,new ArrayList<>()));

            userService.addRoleToUser(2,3);
            userService.addRoleToUser(1,4);
            userService.addRoleToUser(2,5);
            userService.addRoleToUser(1,5);


            itemService.addNewItem(new Item("beef burger", 60, 100));
            itemService.addNewItem(new Item("chicken burger", 55, 200));







//            Set<Item> items =new HashSet<Item>();
//            items.add(item1);
//            items.add(item2);
//            CustomerOrder order1 = new CustomerOrder(
//                    "01-01-2020",
//                    130.0,
//                    user1
//
//
//            );
//            order1.setItems(items);
//            CustomerOrder order2 = new CustomerOrder(
//                    "01-01-2020",
//                    190.0,
//                    user2
//
//            );
//            order2.setItems(items);

//
//            orderRepository.saveAll(List.of(order1,order2));
//            itemRepository.saveAll(List.of(item1,item2));



        };
    };
}
