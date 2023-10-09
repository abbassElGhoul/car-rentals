package com.pc.global.car.renting.carRentals;

import com.pc.global.car.renting.carRentals.dto.RentCarDto;
import com.pc.global.car.renting.customeResponse.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/car-rental")
@RequiredArgsConstructor
public class CarRentalsController
{

    private final CarRentalsService carRentalsService;
    @PostMapping("car-rental/rent-car")
    public Response rentCar(@RequestBody RentCarDto rentCarDto)
    {
        return carRentalsService.rentCat(rentCarDto);
    }
}
