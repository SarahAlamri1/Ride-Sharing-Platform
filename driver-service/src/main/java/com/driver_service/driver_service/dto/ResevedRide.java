package com.driver_service.driver_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResevedRide {
    private String rideSource;
    private String rideDestination;
}
