package com.pc.global.car.renting.insurance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InsuranceRepository extends JpaRepository<InsuranceEntity, Long>
{
    @Query(value = "select * from insurance where car_rental_id in" +
            " (select id from car_rentals where car_id = 4)",nativeQuery = true)
    List<InsuranceEntity> findByCarId(String carId);

    List<InsuranceEntity> findByCarRentalId(Long carRentalId);
}
