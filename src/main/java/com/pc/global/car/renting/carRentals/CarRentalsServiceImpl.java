package com.pc.global.car.renting.carRentals;

import com.pc.global.car.renting.car.CarService;
import com.pc.global.car.renting.carRentals.dto.RentCarDto;
import com.pc.global.car.renting.customeResponse.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class CarRentalsServiceImpl implements CarRentalsService
{
    private final CarRentalsRepository carRentalsRepository;
    private final CarService carService;

    public Response rentCat(RentCarDto rentCarDto)
    {
        try
        {

            CarRentalsEntity carRentalsEntity = carRentalsRepository.save(new CarRentalsEntity(rentCarDto.getClientId(),
                    rentCarDto.getCarId(),
                    rentCarDto.getRentalStartDate(), rentCarDto.getRentalEndDate()));
            Response updateCarResponse = carService.updateCarStatus(rentCarDto.getCarId(), true);
            if (!updateCarResponse.getStatus().equals(HttpStatus.OK))
            {
                return updateCarResponse;
            }
            else
            {
                return new Response(carRentalsEntity);
            }

        }
        catch (DataIntegrityViolationException e)
        {
            log.info(e);
            return (new Response(HttpStatus.CONFLICT, "car already rented"));
        }
        catch (Exception e)
        {
            log.error(e);
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    public Response getCarsByClientId(String clientId)
    {
        try
        {
            Optional<CarRentalsEntity> carRentalsEntity = carRentalsRepository.findByClientId(clientId);
            if (carRentalsEntity.isPresent())
            {
                return new Response(carRentalsEntity);
            }
            else
            {
                log.info("no car rented by client:{}", clientId);
                return new Response(HttpStatus.NO_CONTENT, "no cars rented by client: " + clientId);
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, "error occurred while getting cars by clientId");
        }
    }
}
