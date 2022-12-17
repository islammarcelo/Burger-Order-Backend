package com.example.demo;
import com.example.demo.item.Item;
import com.example.demo.item.ItemService;
import com.example.demo.order.CustomerOrder;
import com.example.demo.order.OrderService;
import com.example.demo.user.BurgerUser;
import com.example.demo.user.Role;
import com.example.demo.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.HashSet;

@Configuration
public class ProjectConfig {

    @Bean
    CommandLineRunner commandLineRunnerUser(
            UserService userService, ItemService itemService, OrderService orderService){
        return args -> {
            userService.saveRole(new Role("user"));
            userService.saveRole(new Role("admin"));
            BurgerUser burgerUser1 = new BurgerUser("islam","adel",12345,new ArrayList<>());
            userService.registerNewUser(burgerUser1);
            BurgerUser burgerUser2 = new BurgerUser("hamada","adel",123456,new ArrayList<>());
            userService.registerNewUser(burgerUser2);
            BurgerUser burgerUser3 = new BurgerUser("gehad","adel",123457,new ArrayList<>());
            userService.registerNewUser(burgerUser3);

            userService.addRoleToUser("user",12345);
            userService.addRoleToUser("user",123456);
            userService.addRoleToUser("admin",123457);
            userService.addRoleToUser("admin",12345);

            Item item1 = new Item("beef burger", 60, 100);
            itemService.addNewItem(item1);
            Item item2 = new Item("chicken burger", 55, 200);
            itemService.addNewItem(item2);


            CustomerOrder order1 = new CustomerOrder(
                    "01-01-2020",
                    130.0,
                    burgerUser1,
                    new HashSet<>()
            );
            order1.getItems().add(item1);
            order1.getItems().add(item2);
            orderService.AddOrder(order1);





        };
    };
}
