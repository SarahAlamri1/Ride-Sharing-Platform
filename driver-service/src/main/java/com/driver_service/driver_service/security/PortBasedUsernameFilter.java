package com.driver_service.driver_service.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Data
public class PortBasedUsernameFilter extends OncePerRequestFilter {


    private String username;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        int serverPort = request.getServerPort();
        username = request.getHeader("X-USERNAME");
        System.out.println("X-USERNAME: " + username);
        System.out.println("serverPort: " + serverPort);
        if (serverPort == 8765) {
            String username = request.getHeader("X-USERNAME");
            if (username == null || username.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"status\":\"error\", \"message\":\"Missing X-USERNAME header\"}");
                return;
            }
            System.out.println("DEBUG: Username from header = " + username);
        }

        filterChain.doFilter(request, response);
    }
}

