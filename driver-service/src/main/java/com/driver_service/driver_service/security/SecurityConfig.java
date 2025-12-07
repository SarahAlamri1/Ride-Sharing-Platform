package com.driver_service.driver_service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final PortBasedUsernameFilter usernameFilter;

    public SecurityConfig(PortBasedUsernameFilter usernameFilter) {
        this.usernameFilter = usernameFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers(HttpMethod.GET, "/driver/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/driver/**").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/driver/**").permitAll()
                                .anyRequest().permitAll()

                )
                .addFilterAt(usernameFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

