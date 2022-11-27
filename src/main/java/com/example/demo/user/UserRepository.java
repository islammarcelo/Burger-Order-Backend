package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<BurgerUser, Integer> {

    @Query("SELECT u FROM BurgerUser u WHERE u.phoneNumber = ?1")
    Optional<BurgerUser> findUserByPhoneNumber(int phoneNumber);

}
