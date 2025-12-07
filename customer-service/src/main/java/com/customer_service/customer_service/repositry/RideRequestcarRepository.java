package com.customer_service.customer_service.repositry;

import com.customer_service.customer_service.dto.customerRideRequest;
import com.customer_service.customer_service.entity.RideRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideRequestcarRepository extends JpaRepository<RideRequest, Integer> {
    List<RideRequest> findByUsername(String username);
    List<RideRequest> findByDriverNameIsNull();
}

