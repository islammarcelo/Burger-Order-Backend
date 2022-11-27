package com.example.demo;

import com.example.demo.items.Item;
import com.example.demo.items.ItemRepository;
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

}
