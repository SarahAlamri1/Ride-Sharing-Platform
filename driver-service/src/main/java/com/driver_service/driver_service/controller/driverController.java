package com.driver_service.driver_service.controller;

import com.driver_service.driver_service.dto.ApiResponse;
import com.driver_service.driver_service.dto.ResevedRide;
import com.driver_service.driver_service.dto.RideDto;
import com.driver_service.driver_service.dto.RideStatus;
import com.driver_service.driver_service.entity.Driver;
import com.driver_service.driver_service.entity.Ride;
import com.driver_service.driver_service.repositry.DriverRepository;
import com.driver_service.driver_service.security.PortBasedUsernameFilter;
import com.driver_service.driver_service.service.CustomerServiceClient;
import com.driver_service.driver_service.service.CustomerSyncService;
import com.driver_service.driver_service.service.DriverService;
import com.driver_service.driver_service.service.RideService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/driver")
public class driverController {

    @Autowired
    private  RideService rideService;
    @Autowired
    private CustomerServiceClient customerServiceClient;
    @Autowired
    private DriverService driverService;
    @Autowired
    private PortBasedUsernameFilter portBasedUsernameFilter;

    @GetMapping("/rides")
    public ResponseEntity<ApiResponse<List<RideDto>>> getAvailableRides() {
        ResponseEntity<List<RideDto>> response = customerServiceClient.getAllRideReq();

        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("Failed to fetch rides from customer service", null));
        }
        if (response.getBody() == null || response.getBody().isEmpty()) {
            return ResponseEntity.ok(
                    new ApiResponse<>("No available rides found", List.of())
            );
        }
        return ResponseEntity.ok(
                new ApiResponse<>("Rides fetched successfully", response.getBody())
        );
    }

    @GetMapping("/my-ride/{userId}")
    public ResponseEntity<ApiResponse<List<Ride>>> getMyRides(@PathVariable String userId) {
        List<Ride> rides = rideService.getDriverRides(userId);
        if (rides.isEmpty()) {
            return ResponseEntity.ok(new ApiResponse<>("No rides found for this user.", rides));
        }
        return ResponseEntity.ok(new ApiResponse<>("Rides retrieved successfully for :"+userId, rides));
    }


    @PutMapping("/status")
    public ResponseEntity<ApiResponse<String>> updateDriverStatus( @RequestParam String driverName, @RequestParam String status) {

        Driver driver = driverService.getDriverById(driverName);
        RideStatus newStatus;

        try {
            newStatus = RideStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>("Invalid status. Allowed values: ONLINE, OFFLINE", null));
        }
        if (driver == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("Driver not found", null));
        }

        driver.setDriverStatus(newStatus);
        driverService.saveDriver(driver);
        return ResponseEntity.ok(new ApiResponse<>("Driver status updated successfully", null));
    }


    @PutMapping("/add-driver")
    public ResponseEntity<ApiResponse<Ride>> addDriver( @RequestParam int RideId,@RequestParam String driverName) {

        Driver driver = driverService.getDriverById(driverName);

        if (driver == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("Driver not found", null));
        }
        if (driver.getDriverStatus() == RideStatus.OFFLINE) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>("cannot be assigned  you are offline, Please update your status", null));
        }

        String driverUserName = portBasedUsernameFilter.getUsername();
        System.out.println("portBasedUsernameFilter.getUsername();: "+driverUserName);
        ResponseEntity<ApiResponse<RideDto>> customerResponse = customerServiceClient.updateRide(RideId, driverName);

        if (customerResponse.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("Unable to assign driver at the moment. Please try again.", null));
        }
        RideDto rideData = customerResponse.getBody().getData();
        if (rideData == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(" Please try again.", null));
        }
        Ride ride = new Ride();
        ride.setRideSource(rideData.getRideSource());
        ride.setRideDestination(rideData.getRideDestination());
        Ride assignedRide = rideService.assignDriver(ride, driverUserName);

        return ResponseEntity.ok(new ApiResponse<>("Driver assigned successfully", assignedRide));
    }

}
