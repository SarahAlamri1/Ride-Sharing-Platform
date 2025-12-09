package com.driver_service.driver_service.service;

import com.driver_service.driver_service.dto.RideStatus;
import com.driver_service.driver_service.entity.Driver;
import com.driver_service.driver_service.repositry.DriverRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class DriverService {
    private DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public void saveDriver(Driver driver) {
        driverRepository.save(driver);
    }

    public Driver getDriverById(String id) {
        return driverRepository.findById(id).get();
    }

    public boolean isDriverOnline(String username) {
        Driver driver = getDriverById(username);
        if (driver == null) return false;
        return driver.getDriverStatus() == RideStatus.ONLINE;
    }

}
