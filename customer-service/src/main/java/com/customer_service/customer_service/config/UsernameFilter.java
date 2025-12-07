package com.customer_service.customer_service.config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Data
public class UsernameFilter extends OncePerRequestFilter  {

    private String username;

    @Autowired
    HttpSession session;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        int serverPort = request.getServerPort();

        if (username != null ) {
             username = request.getHeader("X-USERNAME");
             System.out.println("X-USERNAME: " + username);
            if (username == null || username.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"status\":\"error\", \"message\":\"Missing X-USERNAME header\"}");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    public String getUsername() {
        return username;
    }
}

