package com.pc.global.car.renting.car;


import com.pc.global.car.renting.car.dto.CarDto;
import com.pc.global.car.renting.customeResponse.Response;
import org.springframework.stereotype.Service;

@Service
public interface CarService
{
    Response getAllCars();

    Response addCar(CarDto carDto);

    Response updateCarStatus(String carId, boolean status);

    Response deleteCar(String carId);

    Response getCar(String carId);
}
