package com.example.demo.user;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
public class UserController {
    final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUsers")
    public List<BurgerUser> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/createUser")
    public void registerNewUser(@RequestBody BurgerUser burgerUser){
        userService.registerNewUser(burgerUser);
    }

    @PostMapping("/save/role")
    public void saveRole(@RequestBody Role role){
        userService.saveRole(role);
    }

    @PostMapping("/add/roleToUser")
    public void addRoleToUser(@RequestBody RoleUserForm roleUserForm){
        String roleName = roleUserForm.roleName;
        int userPhone = roleUserForm.userPhoneNumber;

        userService.addRoleToUser(roleName,userPhone);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") int userId){
        userService.deleteUser(userId);
    }

/*    @PutMapping(path = "{userId}")
    public void updateUser(@PathVariable("userId") int userId,
                           @RequestParam(required = false) String fName,
                           @RequestParam(required = false) String lName,
                           @RequestParam(required = false) int phone){
        userService.updateUser(userId,fName,lName,phone);
    }
 */

}
@Data
class RoleUserForm{
    String roleName;
    int userPhoneNumber;

}
