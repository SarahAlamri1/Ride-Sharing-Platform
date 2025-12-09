package com.driver_service.driver_service.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RideDto {
    private int RideId;
    private String rideSource;
    private String rideDestination;
}
