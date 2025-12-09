package com.customer_service.customer_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "ride_requests")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RideRequest {

    @Id
    @Column(nullable = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int RideId;

    @Column(nullable = true, unique = true)
    private String username;

    @Column(nullable = true)
    private String rideSource;

    @Column(nullable = true)
    private String rideDestination;

    @Column(nullable = true)
    private String driverName;

    @Column(nullable = true)
    private String rideStatus;

    @Override
    public String toString() {
        return "RideRequest{" +
                ", username='" + username + '\'' +
                ", rideSource='" + rideSource + '\'' +
                ", rideDestination='" + rideDestination + '\'' +
                ", driverName='" + driverName + '\'' +
                ", rideStatus=" + rideStatus +
                '}';
    }
}
