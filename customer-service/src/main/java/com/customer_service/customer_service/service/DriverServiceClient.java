package com.customer_service.customer_service.service;

import com.customer_service.customer_service.dto.ApiResponse;
import com.customer_service.customer_service.dto.DriverRideRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class DriverServiceClient {

    private final RestTemplate restTemplate;
    private static final String DRIVER_SERVICE_URL = "http://localhost:8082/driver/ride";

    public DriverServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Retry(name = "driverService", fallbackMethod = "fallback")
    @CircuitBreaker(name = "driverService", fallbackMethod = "fallback")
    @RateLimiter(name = "driverService", fallbackMethod = "fallback")
    public ResponseEntity<ApiResponse<?>>  addRide(@RequestBody DriverRideRequest ride){
        System.out.println(" Attempting to update customer-service, rideId={}");

        HttpEntity<DriverRideRequest> request = new HttpEntity<>(ride);

        ResponseEntity<ApiResponse<?>> response = restTemplate.exchange(
                DRIVER_SERVICE_URL,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<ApiResponse<?>>() {}
        );
        System.out.println(" Successfully Send driver-service");
        return response;
    }

    public ResponseEntity<ApiResponse<?>> fallback(DriverRideRequest ride, Throwable e) {
        System.out.println(" Driver-service is DOWN");

        ApiResponse<?> fallbackResponse = new ApiResponse<>(
                "Ride updated locally, but driver-service is DOWN. Retry will be attempted later.",
                null
        );
        return ResponseEntity.status(503).body(fallbackResponse);
    }
}

