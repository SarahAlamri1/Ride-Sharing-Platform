package com.customer_service.customer_service.security;

import com.customer_service.customer_service.config.ApiKeyFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final ApiKeyFilter apiKeyFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth ->
//                        auth
//                                .requestMatchers(HttpMethod.POST, "/customer/**").permitAll()
//                                .requestMatchers(HttpMethod.GET, "/customer/**").permitAll()
//                                .requestMatchers(HttpMethod.PUT, "/customer/**").permitAll()
//                                .anyRequest().permitAll()
//
//                )
                .addFilterBefore(apiKeyFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
}

