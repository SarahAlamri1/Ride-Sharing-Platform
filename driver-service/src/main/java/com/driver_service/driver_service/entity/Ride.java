package com.driver_service.driver_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ride")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rideId;

    @Column(name = "ride_source")
    private String rideSource;

    @Column(name = "ride_destination")
    private String rideDestination;

    @ManyToOne
    @JoinColumn(name = "username")
    @JsonBackReference
    private Driver driver;
}

