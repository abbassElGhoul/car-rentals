package com.pc.global.car.renting.carRentals;

import com.pc.global.car.renting.car.CarService;
import com.pc.global.car.renting.carRentals.dto.CarDetailsDto;
import com.pc.global.car.renting.carRentals.dto.RentCarDto;
import com.pc.global.car.renting.client.ClientEntity;
import com.pc.global.car.renting.client.ClientRepository;
import com.pc.global.car.renting.customeResponse.Response;
import com.pc.global.car.renting.sponser.SponsorEntity;
import com.pc.global.car.renting.sponser.SponsorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class CarRentalsServiceImpl implements CarRentalsService
{
    private final CarRentalsRepository carRentalsRepository;
    private final CarService carService;
    private final ClientRepository clientRepository;
    private final SponsorRepository sponsorRepository;

    public Response rentCar(RentCarDto rentCarDto)
    {
        try
        {

            CarRentalsEntity carRentalsEntity = carRentalsRepository.save(new CarRentalsEntity(rentCarDto.getClientId(),
                    rentCarDto.getCarId(),
                    rentCarDto.getRentalStartDate(), rentCarDto.getRentalEndDate()));
            Response updateCarResponse = carService.updateCarStatus(rentCarDto.getCarId(), Boolean.TRUE);
            Response updateClientStatus = updateClientStatus(rentCarDto.getClientId(), Boolean.TRUE);
            if (!updateCarResponse.getStatus().equals(HttpStatus.OK))
            {
                return updateCarResponse;
            }
            if (!updateClientStatus.getStatus().equals(HttpStatus.OK))
            {
                return updateClientStatus;
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

    public Response getCarDetails(String carId)
    {
        try
        {
            List<CarDetailsDto> details = new ArrayList<>();
            List<CarRentalsEntity> carRentals = carRentalsRepository.findByCarId(carId);

            for (CarRentalsEntity carRental : carRentals)
            {
                Optional<ClientEntity> client = clientRepository.findById(carRental.getClientId());

                if (client.isPresent())
                {
                    Optional<SponsorEntity> sponsor = sponsorRepository.findById(client.get().getSponsorId());
                    String sponsorName = sponsor.map(SponsorEntity::getName).orElse(null);
                    String sponsorPhoneNumber = sponsor.map(SponsorEntity::getPhoneNumber).orElse(null);

                    CarDetailsDto carDetails = new CarDetailsDto(
                            client.get().getName(),
                            sponsorName,
                            sponsorPhoneNumber,
                            carRental.getRentalStartDate(),
                            carRental.getRentalEndDate()
                    );
                    details.add(carDetails);
                }
            }

            return new Response(details);
        }
        catch (Exception e)
        {
            log.error("An error occurred while getting car details" + e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while getting car details");
        }
    }

    public Response updateClientStatus(String clientId, Boolean status)
    {
        Optional<ClientEntity> client = clientRepository.findById(clientId);

        if (client.isPresent())
        {
            if (client.get().getCurrentlyRenting())
            {
                return (new Response(HttpStatus.CONFLICT, "client already rented"));
            }
            client.get().setCurrentlyRenting(status);
            client.get().setTotalRentals(client.get().getTotalRentals() +1);
            return (new Response(clientRepository.save(client.get())));
        }
        else
        {
            return new Response(HttpStatus.NO_CONTENT, "no such client found");
        }

    }

}
