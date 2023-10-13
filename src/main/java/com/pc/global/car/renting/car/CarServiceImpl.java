package com.pc.global.car.renting.car;

import com.pc.global.car.renting.car.dto.CarDto;
import com.pc.global.car.renting.customeResponse.Response;
import com.pc.global.car.renting.helper.PhotoHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Log4j2
public class CarServiceImpl implements CarService
{
    private final CarRepository carRepository;

    @Value("${photos.path.cars}")
    private String carImagesDefaultPath;

    public Response getAllCars()
    {
        try
        {
            List<CarEntity> cars = carRepository.findAll();
            log.info("find all cars:{}", cars);
            if (!cars.isEmpty())
            {
                return new Response(cars);
            }
            else
            {
                log.info("no data found in cars");
                return new Response(HttpStatus.NO_CONTENT, "No data found");

            }
        }
        catch (Exception e)
        {
            log.error("getAllCars error:{}", e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public Response addCar(CarDto carDto)
    {
        try
        {
            UUID carId = UUID.randomUUID();
            Response storeFileResponse = PhotoHelper.storeFiles(carId.toString(), carDto.getCarImage(), carImagesDefaultPath, "car", true);
            if (!storeFileResponse.getStatus().equals(HttpStatus.OK))
            {
                return storeFileResponse;

            }
            else
            {
                CarEntity car = new CarEntity(String.valueOf(carId), carDto.getLicensePlate(), carDto.getMakeModel(),
                        carDto.getRentingStatus(), String.valueOf(storeFileResponse.getData()).replace("\\\\", "\\"));

                return new Response(carRepository.save(car));
            }
        }
        catch (Exception e)
        {
            log.error("error while storing files:{}", e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, "error while storing files");

        }
    }

    public Response updateCarStatus(String carId, Boolean status)
    {
        Optional<CarEntity> car = carRepository.findById(carId);

        if (car.isPresent())
        {
            if (!car.get().getRentingStatus())
            {
                return (new Response(HttpStatus.CONFLICT, "car already rented"));
            }
            car.get().setRentingStatus(status);
            return (new Response(carRepository.save(car.get())));
        }
        else
        {
            return new Response(HttpStatus.NO_CONTENT, "no such car found");
        }

    }

    public Response deleteCar(String carId)
    {
        try
        {
            carRepository.deleteById(carId);
            return new Response("car: " + carId + " deleted");
        }
        catch (EmptyResultDataAccessException e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.NO_CONTENT, "cannot delete car with id: " + carId + " car not found");

        }
        catch (DataIntegrityViolationException e)
        {
            log.error(e);
            return new Response(HttpStatus.NOT_ACCEPTABLE, "car already rented");
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.NO_CONTENT, "no such car found");
        }
    }

    @Override
    public Response getCar(String carId)
    {
        try
        {
            Optional<CarEntity> car = carRepository.findById(carId);

            return car.map(Response::new).orElseGet(() -> new Response(HttpStatus.NO_CONTENT, "no such car found"));
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.NO_CONTENT, "no such car found");
        }
    }
}
