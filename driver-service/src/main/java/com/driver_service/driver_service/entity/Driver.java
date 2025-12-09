package com.driver_service.driver_service.entity;

import com.driver_service.driver_service.dto.RideStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "driver")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {

    @Id
    @Column(nullable = false)
    private String username;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Ride> rides;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RideStatus driverStatus = RideStatus.OFFLINE;
}


