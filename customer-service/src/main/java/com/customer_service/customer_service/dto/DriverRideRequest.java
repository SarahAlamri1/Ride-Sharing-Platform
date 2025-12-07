package com.customer_service.customer_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverRideRequest {
    private int RideId;
    private String rideSource;
    private String rideDestination;
}
