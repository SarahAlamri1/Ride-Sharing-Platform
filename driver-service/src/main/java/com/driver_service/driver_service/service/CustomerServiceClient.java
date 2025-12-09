package com.driver_service.driver_service.service;

import com.driver_service.driver_service.dto.ApiResponse;
import com.driver_service.driver_service.dto.RideDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class CustomerServiceClient {

    private final RestTemplate restTemplate;
    private static final String CUSTOMER_SERVICE_URL = "http://customer-service:8081/customer";
    private static final String API_KEY = "my-secret-key";
    private static HttpEntity<Void> entity;

    @Autowired
    public CustomerServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", API_KEY);
         entity = new HttpEntity<>(headers);
    }

    public ResponseEntity<List<RideDto>> getAllRideReq() {
        String url = CUSTOMER_SERVICE_URL + "/rides";

        ResponseEntity<List<RideDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {}
        );
        return response;
    }

    @Transactional
    public ResponseEntity<ApiResponse<RideDto>> updateRide(int id, String driverName) {

        String url = CUSTOMER_SERVICE_URL + "/ride" + "?RideId=" + id + "&driverName=" + driverName;

        ResponseEntity<ApiResponse<RideDto>> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                entity,
                new ParameterizedTypeReference<ApiResponse<RideDto>>() {}
        );
        return response;
    }
}
