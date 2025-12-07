package com.driver_service.driver_service.service;

import com.driver_service.driver_service.dto.ApiResponse;
import com.driver_service.driver_service.dto.RideDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@Slf4j
public class CustomerServiceClient {

    private final RestTemplate restTemplate;
    private static final String CUSTOMER_SERVICE_URL = "http://customer-service:8081/customer";

    @Autowired
    public CustomerServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<List<RideDto>> getAllRideReq() {
        String url = CUSTOMER_SERVICE_URL + "/rides";
        ResponseEntity<List<RideDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response;
    }

    //    @Retry(name = "customerService", fallbackMethod = "fallback")
//    @CircuitBreaker(name = "customerService", fallbackMethod = "fallback")
//    @RateLimiter(name = "customerService", fallbackMethod = "fallback")
    public ResponseEntity<ApiResponse<RideDto>> updateRide(int id, String driverName) {
        System.out.println("🔁 Attempting to update customer-service, rideId={}");

        String url = CUSTOMER_SERVICE_URL + "/ride" + "?RideId=" + id + "&driverName=" + driverName;

        ResponseEntity<ApiResponse<RideDto>> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                null,
                new ParameterizedTypeReference<ApiResponse<RideDto>>() {}
        );
        System.out.println("✅ Successfully updated customer-service, rideId={}");
        return response;
    }

    public ResponseEntity<ApiResponse<?>> fallback(int id, String driverName, Throwable e) {
        System.out.println("❌ Customer-service is DOWN, rideId={}, reason={}");
        ApiResponse<?> fallbackResponse = new ApiResponse<>(
                "Ride updated locally, but customer-service is DOWN. Retry will be attempted later.",
                null
        );

        return ResponseEntity.status(503).body(fallbackResponse);
    }
}
