package com.example.smth;
import com.example.smth.JWT.JwtSessionFilter;
import com.example.smth.JWT.JwtTokenService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    private final JwtTokenService jwtTokenService;

    public FilterConfig(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @Bean
    public FilterRegistrationBean<JwtSessionFilter> jwtSessionFilter() {
        FilterRegistrationBean<JwtSessionFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtSessionFilter(jwtTokenService));
        registrationBean.addUrlPatterns("/api/*"); // Применяется ко всем эндпоинтам, начинающимся с /api/
        return registrationBean;
    }
}