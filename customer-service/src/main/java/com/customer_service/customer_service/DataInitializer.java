package com.customer_service.customer_service;


import com.customer_service.customer_service.dto.RideStatus;
import com.customer_service.customer_service.entity.RideRequest;
import com.customer_service.customer_service.repositry.RideRequestcarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {


    private RideRequestcarRepository rideRequestcarRepository;


 private DataInitializer(RideRequestcarRepository rideRequestcarRepository){
     this.rideRequestcarRepository = rideRequestcarRepository;

 }

 @Override
    public void run(String... args) throws Exception {
     CreaterRequest();
 }

 private void CreaterRequest(){

//     if (userRepository.count() > 0) {
//         System.out.println("Existing users found. ");
////         return;
//         userRepository.deleteAll();
//     }

     for(int i = 0; i < 5; i++) {
         RideRequest rideRequest = new RideRequest();
         rideRequest.setUsername("customer"+i);
         rideRequest.setRideStatus(RideStatus.WAITING.getValue());
         rideRequest.setRideSource("Malga");
         rideRequest.setRideDestination("KAFD");
         rideRequestcarRepository.save(rideRequest);
     }

    // System.out.println("Users created successfully!");
 }
}
