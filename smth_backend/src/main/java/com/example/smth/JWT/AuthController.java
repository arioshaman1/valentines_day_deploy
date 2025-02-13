package com.example.smth.JWT;

import com.example.smth.entities.UserEntity;
import com.example.smth.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final JwtTokenService jwtTokenService;
    private final UserService userService;

    public AuthController(JwtTokenService jwtTokenService, UserService userService) {
        this.jwtTokenService = jwtTokenService;
        this.userService = userService;
    }

    // Логин пользователя и сохранение токена в сессию
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        UserEntity user = userService.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if (user != null) {
            String token = jwtTokenService.generateToken(user.getUsername());
            session.setAttribute("jwt", token);  // Сохраняем JWT токен в сессию
            return "Logged in successfully";
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    // Логаут и удаление токена из сессии
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("jwt");  // Удаляем JWT токен из сессии
        return "Logged out successfully";
    }

    // Проверка текущего пользователя на основе токена в сессии
    @GetMapping("/current")
    public String getCurrentUser(HttpSession session) {
        String token = (String) session.getAttribute("jwt");
        if (token != null && jwtTokenService.validateToken(token)) {
            String username = jwtTokenService.getUsernameFromToken(token);
            return "Current user: " + username;
        } else {
            return "No valid token found";
        }
    }
}