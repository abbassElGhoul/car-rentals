package com.pc.global.car.renting.carRentals;

import com.pc.global.car.renting.carRentals.dto.RentCarDto;
import com.pc.global.car.renting.customeResponse.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/car-rental")
@RequiredArgsConstructor
@Log4j2
public class CarRentalsController
{

    private final CarRentalsService carRentalsService;

    @PostMapping("rent-car")
    public Response rentCar(@RequestBody RentCarDto rentCarDto)
    {
        try
        {
            return carRentalsService.rentCar(rentCarDto);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    @GetMapping("get-car-details")
    public Response getCarDetails(@RequestParam String carId)
    {
        try
        {
            return carRentalsService.getCarDetails(carId);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
