package com.example.smth.Controller;

import com.example.smth.entities.UserEntity;
import com.example.smth.entities.OrderEntity;
import com.example.smth.service.UserService;
import com.example.smth.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    // Получить текущего пользователя
    @GetMapping("/current")
    public ResponseEntity<UserEntity> getCurrentUser(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        UserEntity user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Получить список всех пользователей
    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Создание валентинки (заказ)
    @PostMapping("/valentines")
    public ResponseEntity<String> sendValentine(@RequestBody OrderEntity order, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
        }
        UserEntity user = userService.getUserById(userId);
        order.setUser(user); // Устанавливаем пользователя как отправителя валентинки
        orderService.save(order); // Сохраняем ордер в базе
        return new ResponseEntity<>("Valentine sent successfully!", HttpStatus.OK);
    }

    // Регистрация пользователя
    @PostMapping("/register")
    public ResponseEntity<UserEntity> registerUser(@RequestBody UserEntity user) {
        if (userService.getUserByEmail(user.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Пользователь с таким email уже существует
        }
        UserEntity createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // Логин пользователя
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserEntity loginUser, HttpSession session) {
        UserEntity user = userService.getUserByEmail(loginUser.getEmail());

        if (user == null || !user.getPassword().equals(loginUser.getPassword())) {
            return new ResponseEntity<>("Login failed", HttpStatus.UNAUTHORIZED); // Неверный email или пароль
        } else {
            // Сохраняем в сессии ID пользователя
            session.setAttribute("userId", user.getId());
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        }
    }

    // Логаут пользователя
    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(HttpSession session) {
        session.invalidate(); // Удалить сессию
        return new ResponseEntity<>("Logout successful", HttpStatus.OK);
    }
}