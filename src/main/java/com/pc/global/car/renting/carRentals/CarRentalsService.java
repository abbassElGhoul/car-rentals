package com.pc.global.car.renting.carRentals;

import com.pc.global.car.renting.carRentals.dto.RentCarDto;
import com.pc.global.car.renting.customeResponse.Response;
import org.springframework.stereotype.Service;

@Service
public interface CarRentalsService
{
    Response rentCar(RentCarDto rentCarDto);
    Response getCarsByClientId(String clientId);

    Response getCarDetails(String carId);

}
