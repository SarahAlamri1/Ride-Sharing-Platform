package com.driver_service.driver_service.repositry;

import com.driver_service.driver_service.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, String> {
}
