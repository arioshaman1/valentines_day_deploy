package com.example.smth;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Применить ко всем эндпоинтам
                .allowedOrigins("http://217.196.107.39:8081") // Разрешить запросы с этого домена
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Разрешить методы
                .allowedHeaders("*") // Разрешить все заголовки
                .allowCredentials(true); // Разрешить передачу кук и заголовков авторизации
    }
}