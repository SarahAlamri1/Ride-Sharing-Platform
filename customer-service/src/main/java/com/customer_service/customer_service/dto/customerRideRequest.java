package com.customer_service.customer_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class customerRideRequest {
    private String customerId;
    private String rideSource;
    private String rideDestination;
}