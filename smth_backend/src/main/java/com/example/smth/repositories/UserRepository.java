package com.example.smth.repositories;

import com.example.smth.entities.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);  // Поиск по email
    UserEntity findByUsername(String username);
    UserEntity findByUsernameAndPassword(String username, String password);
}
