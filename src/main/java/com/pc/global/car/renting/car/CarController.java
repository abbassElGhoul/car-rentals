package com.pc.global.car.renting.car;

import com.pc.global.car.renting.car.dto.CarDto;
import com.pc.global.car.renting.customeResponse.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/car")
@RequiredArgsConstructor
@Log4j2
public class CarController
{

    private final CarService carService;

    @GetMapping("get-all-cars")
    public Response getAllCars()
    {
        try
        {
            return carService.getAllCars();
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping(value = "add-car", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Response addCar(@ModelAttribute CarDto carDto)
    {
        try
        {
            return carService.addCar(carDto);
        }
        catch (Exception e)
        {
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("delete-car")
    public Response deleteCar(@RequestParam String carId)
    {
        try
        {
            return carService.deleteCar(carId);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

}
