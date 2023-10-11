package com.pc.global.car.renting.carRentals;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRentalsRepository extends JpaRepository<CarRentalsEntity, Long>
{
    @Query(value = "select * from car_rentals where client_id = :clientId limit 1", nativeQuery = true)
    Optional<CarRentalsEntity> findByClientId(String clientId);

    List<CarRentalsEntity> findByCarId(String carId);
}
