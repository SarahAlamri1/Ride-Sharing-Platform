package com.driver_service.driver_service.repositry;
import com.driver_service.driver_service.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RideRepository extends JpaRepository<Ride, Integer> {
    List<Ride> findByDriverUsername(String username);
}
