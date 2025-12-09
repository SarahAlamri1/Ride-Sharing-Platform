package com.customer_service.customer_service.controller;

import com.customer_service.customer_service.dto.ApiResponse;
import com.customer_service.customer_service.dto.RideStatus;
import com.customer_service.customer_service.dto.customerRideRequest;
import com.customer_service.customer_service.dto.DriverRideRequest;
import com.customer_service.customer_service.entity.RideRequest;
import com.customer_service.customer_service.service.DriverServiceClient;
import com.customer_service.customer_service.service.RideRequestService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@NoArgsConstructor
@RequestMapping("/customer")
public class custemerController {

    private  RideRequestService rideRequestService;

    @Autowired
    public custemerController( RideRequestService rideRequestService, DriverServiceClient driverServiceClient) {
        this.rideRequestService = rideRequestService;
    }

    @GetMapping("/rides")
    public List<DriverRideRequest> getAllRideReq() {
        return rideRequestService.findByDriverNameIsNull();
    }

    @GetMapping("/my_rides")
    public List<RideRequest> getMyRides(@RequestHeader("X-USERNAME") String username) {
        return rideRequestService.getRidesByUser(username);
    }

    @PostMapping("/ride")
    public ResponseEntity<ApiResponse<RideRequest>> requestRide( @RequestHeader("X-USERNAME") String username, @RequestBody customerRideRequest customerRequest) {

        RideRequest rideRequest = new RideRequest();
        rideRequest.setUsername(username);
        rideRequest.setRideSource(customerRequest.getRideSource());
        rideRequest.setRideDestination(customerRequest.getRideDestination());
        rideRequest.setDriverName(null);
        rideRequest.setRideStatus(RideStatus.WAITING.getValue());
        RideRequest saved = rideRequestService.addRideRequest(rideRequest);

        return ResponseEntity.ok(new ApiResponse<>("Ride Created successfully", saved));

    }

    @PutMapping("/ride")
    public ResponseEntity<ApiResponse<DriverRideRequest>> updateRide( @RequestParam int RideId, @RequestParam String driverName) {
        RideRequest ride = rideRequestService.getRideRequestById(RideId);
        if (ride == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("Ride not found", null));
        }

        ride.setDriverName(driverName);
        ride.setRideStatus(RideStatus.ACCEPTED.getValue());
        RideRequest saved = rideRequestService.updateRideRequest(ride);

        DriverRideRequest driverRideRequest = new DriverRideRequest();
        driverRideRequest.setRideSource(saved.getRideSource());
        driverRideRequest.setRideDestination(saved.getRideDestination());

        ApiResponse<DriverRideRequest> response = new ApiResponse<>(
                "Ride request updated successfully",
                driverRideRequest
        );

        return ResponseEntity.ok(response);
    }

}

