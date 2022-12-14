package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    final UserRepository userRepository;
    final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<BurgerUser> getUsers() {
        return userRepository.findAll();
    }

    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    public void addRoleToUser(int roleId, int userId) {
        BurgerUser burgerUser = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException(
                "User with this id " + userId + " dose not exists"));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new IllegalStateException(
                "Role with this id " + roleId + " dose not exists"));

        burgerUser.getRoles().add(role);


    }


    public void registerNewUser(BurgerUser burgerUser) {
        Optional<BurgerUser> userByPhoneNumber = getBurgerUser(burgerUser.getPhoneNumber());

        if (userByPhoneNumber.isPresent()) {
            throw new IllegalStateException("phone taken :) ");
        }
        userRepository.save(burgerUser);
    }

    private Optional<BurgerUser> getBurgerUser(int phoneNumber) {
        Optional<BurgerUser> userByPhoneNumber = userRepository
                .findUserByPhoneNumber(phoneNumber);
        return userByPhoneNumber;
    }

    public void deleteUser(int userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException("User with this id " + userId + " dose not exists");
        }
        userRepository.deleteById(userId);
    }


    public void updateUser(int userId, String fName, String lName, int phone) {
        BurgerUser burgerUser = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException(
                "User with this id " + userId + " dose not exists"));

        if (fName != null && fName.length() > 0 && !Objects.equals(burgerUser.getfName(), fName)) {
            burgerUser.setfName(fName);
        }

        if (lName != null && lName.length() > 0 && !Objects.equals(burgerUser.getlName(), lName)) {
            burgerUser.setlName(lName);
        }

        if (phone != 0 && !Objects.equals(burgerUser.getPhoneNumber(), phone)) {

            Optional<BurgerUser> userByPhoneNumber = getBurgerUser(phone);

            if (userByPhoneNumber.isPresent()) {
                throw new IllegalStateException("phone taken :) ");
            }
            burgerUser.setPhoneNumber(phone);
        }
    }
}
