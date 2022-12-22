package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;



    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BurgerUser burgerUser = userRepository.findUserByUsername(username).orElseThrow(() -> new IllegalStateException(
                "User with this " + username + " dose not exists"));
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        burgerUser.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new User(burgerUser.getUsername(), burgerUser.getPassword(), authorities);
    }

    public List<BurgerUser> getUsers() {
        return userRepository.findAll();
    }

    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    public void addRoleToUser(String roleName, int phoneNumber) {
        BurgerUser burgerUser = getBurgerUser(phoneNumber).orElseThrow(() -> new IllegalStateException(
                "User with this phone " + phoneNumber + " dose not exists"));
        Role role = roleRepository.findRoleByName(roleName).orElseThrow(() -> new IllegalStateException(
                "Role with this " + roleName + " dose not exists"));

        burgerUser.getRoles().add(role);
    }


    public void registerNewUser(BurgerUser burgerUser) {
        Optional<BurgerUser> userByPhoneNumber = getBurgerUser(burgerUser.getPhoneNumber());

        if (userByPhoneNumber.isPresent()) {
            throw new IllegalStateException("phone taken :) ");
        }
        burgerUser.setPassword(passwordEncoder.encode(burgerUser.getPassword()));
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


/*
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
 */


}
