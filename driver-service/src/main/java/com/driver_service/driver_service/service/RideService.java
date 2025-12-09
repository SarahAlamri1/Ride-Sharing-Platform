package com.driver_service.driver_service.service;

import com.driver_service.driver_service.entity.Driver;
import com.driver_service.driver_service.entity.Ride;
import com.driver_service.driver_service.repositry.DriverRepository;
import com.driver_service.driver_service.repositry.RideRepository;
import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@Data
public class RideService {

    private RideRepository rideRepository;
    private DriverRepository driverRepository;

    public RideService(RideRepository rideRepository , DriverRepository driverRepository) {
        this.rideRepository = rideRepository;
        this.driverRepository = driverRepository;
    }

    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }

    public List<Ride> getDriverRides(String username) {
        return rideRepository.findByDriverUsername(username);
    }


    public Ride createRide(Ride ride) {
        return rideRepository.save(ride);
    }

    @Transactional
    public Ride assignDriver(Ride ride, String driverUsername) {
        System.out.println("ride" + ride + "driverUsername" + driverUsername);

//        Ride ride = new Ride();
//        ride.setRideId(rideId);
//        ride.setRideSource(rideData.getRideSource());
//        ride.setRideDestination(rideData.getRideDestination());
//        ride.setDriver(driver);

//        Ride ride = rideRepository.findById(rideId)
//                .orElseThrow(() -> new RuntimeException("Ride not found"));
        System.out.println("assignDriver: ride" + ride );

        Driver driver = driverRepository.findById(driverUsername)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        ride.setDriver(driver);
        return rideRepository.save(ride);
    }


    public Ride updateRide(Ride ride) {
        return rideRepository.save(ride);
    }

}
