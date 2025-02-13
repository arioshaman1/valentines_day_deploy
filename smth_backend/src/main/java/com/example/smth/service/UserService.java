package com.example.smth.service;

import com.example.smth.entities.UserEntity;
import com.example.smth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Получить пользователя по ID
    public UserEntity getUserById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        return user.orElse(null);  // Если пользователь не найден, возвращаем null
    }

    // Создать нового пользователя
    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    // Получить пользователя по email
    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Обновить данные пользователя
    public UserEntity updateUser(Long id, UserEntity userDetails) {
        UserEntity user = getUserById(id);
        if (user != null) {
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            // Можно добавить другие обновляемые поля
            return userRepository.save(user);
        }
        return null;  // Если пользователь не найден
    }
    public UserEntity getAuthenticatedUser(String username) {
        return userRepository.findByEmail(username);
    }


    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
    public UserEntity findByUsernameAndPassword(String username, String password) {
        // Поищем пользователя с таким username и password в базе данных
        return userRepository.findByUsernameAndPassword(username, password);
    }

}