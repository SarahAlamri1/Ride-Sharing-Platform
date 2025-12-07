package com.driver_service.driver_service;

import com.driver_service.driver_service.dto.RideStatus;
import com.driver_service.driver_service.entity.Driver;
import com.driver_service.driver_service.repositry.DriverRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final DriverRepository driverRepository;


 private DataInitializer(DriverRepository driverRepository){
     this.driverRepository = driverRepository;
 }

 @Override
    public void run(String... args) throws Exception {
     CreaterUsers();
 }

 private void CreaterUsers(){

     for(int i = 0; i < 4; i++) {
         Driver driver = new Driver();
         driver.setUsername("driver"+i);
         driver.setDriverStatus(RideStatus.ONLINE);
         driverRepository.save(driver);
     }
     Driver driver = new Driver();
     driver.setUsername("driver4");
     driver.setDriverStatus(RideStatus.OFFLINE);
     driverRepository.save(driver);

    // System.out.println("Drivers created successfully!");
 }
}
