package com.driver_service.driver_service.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerSyncService {
    @Autowired
    private CustomerServiceClient customerServiceClient;

    @Retry(name = "customerService", fallbackMethod = "fallback")
    @CircuitBreaker(name = "customerService", fallbackMethod = "fallback")
    @RateLimiter(name = "customerService", fallbackMethod = "fallback")
    public void syncWithCustomerService(int rideId, String driverName) {

        System.out.print("🔁 Sending update to customer-service");
        customerServiceClient.updateRide(rideId, driverName);
    }

    public void fallback(int rideId, String driverName, Throwable e) {

        System.out.print("❌ Fallback triggered: customer-service is DOWN ");

        System.out.print("Reason: "+ e);
    }
}
