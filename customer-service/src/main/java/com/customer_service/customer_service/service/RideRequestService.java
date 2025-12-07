package com.customer_service.customer_service.service;

import com.customer_service.customer_service.dto.DriverRideRequest;
import com.customer_service.customer_service.dto.customerRideRequest;
import com.customer_service.customer_service.entity.RideRequest;
import com.customer_service.customer_service.repositry.RideRequestcarRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Getter
@Setter
public class RideRequestService {

    private RideRequestcarRepository rideRequestcarRepository;

    public RideRequestService(RideRequestcarRepository rideRequestcarRepository) {
        this.rideRequestcarRepository = rideRequestcarRepository;
    }

    public RideRequest addRideRequest(RideRequest rideRequest) {
        rideRequestcarRepository.save(rideRequest) ;
        return rideRequest;
   }

   public RideRequest updateRideRequest(RideRequest rideRequest) {
       rideRequestcarRepository.save(rideRequest);
       return rideRequest;
   }

   public List<RideRequest> getAllRideRequests() {
        System.out.println("DEBUG: getAllRideRequests:"+rideRequestcarRepository.findAll());
       return rideRequestcarRepository.findAll();
   }

   public RideRequest getRideRequestById(int id) {

       return rideRequestcarRepository.findById(id).get();
//       return toDto(rideRequestcarRepository.findById(id).get())

   }

    public List<RideRequest> getRidesByUser(String username) {
        return rideRequestcarRepository.findByUsername(username);
    }


public DriverRideRequest toDto(RideRequest entity) {
    return new DriverRideRequest(
            entity.getRideId(),
            entity.getRideSource(),
            entity.getRideDestination()
    );
}

public List<DriverRideRequest> findByDriverNameIsNull() {
    return rideRequestcarRepository.findByDriverNameIsNull()
            .stream()
            .map(this::toDto)
            .toList();
}

}
