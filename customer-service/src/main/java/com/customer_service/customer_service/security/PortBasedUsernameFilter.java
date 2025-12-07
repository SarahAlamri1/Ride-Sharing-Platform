package com.customer_service.customer_service.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Getter
public class PortBasedUsernameFilter extends OncePerRequestFilter {

    private String username;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        int serverPort = request.getServerPort();
        username = request.getHeader("X-USERNAME");

        if (serverPort == 8765) {
             username = request.getHeader("X-USERNAME");
            request.setAttribute("username", username);
             System.out.println("in s: X-USERNAME: " + username);
            if (username == null || username.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"status\":\"error\", \"message\":\"Missing X-USERNAME header\"}");
                return;
            }
            System.out.println("DEBUG: Username from header = " + username);
        }

        filterChain.doFilter(request, response);
    }

    public String getUsername() {
        return username;
    }
}



