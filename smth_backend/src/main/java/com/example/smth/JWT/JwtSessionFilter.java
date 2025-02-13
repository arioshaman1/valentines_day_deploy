package com.example.smth.JWT;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class JwtSessionFilter implements Filter {

    private final JwtTokenService jwtTokenService;

    public JwtSessionFilter(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false); // Не создавать новую сессию

        if (session != null) {
            String token = (String) session.getAttribute("jwt");

            if (token != null && jwtTokenService.validateToken(token)) {
                String username = jwtTokenService.getUsernameFromToken(token);
                // Вы можете установить пользователя в контекст или что-то еще
                // Например, здесь можно сохранять данные пользователя в контексте, если необходимо
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}