package com.pc.global.car.renting.insurance;

import com.pc.global.car.renting.customeResponse.Response;
import com.pc.global.car.renting.helper.PhotoHelper;
import com.pc.global.car.renting.insurance.dto.InsuranceDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService
{
    private final InsuranceRepository insuranceRepository;

    @Value("${photos.path.insurance}")
    private String insuranceVideoDefaultPath;

    public Response getAllInsurance()
    {
        try
        {
            List<InsuranceEntity> insuranceEntities = insuranceRepository.findAll();
            log.info("insuranceEntities:{}", insuranceEntities);
            return new Response(insuranceEntities);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while getting all insurance");
        }
    }

    public Response getInsuranceById(Long insuranceId)
    {
        try
        {
            Optional<InsuranceEntity> insuranceEntity = insuranceRepository.findById(insuranceId);
            if (insuranceEntity.isPresent())
            {
                return new Response(insuranceEntity);
            }
            else
            {
                log.warn("no insurance found with such id:{}", insuranceId);
                return new Response(HttpStatus.NO_CONTENT, "no insurance found with such id: " + insuranceId);
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error occurred while getting insurance with id: " + insuranceId);
        }

    }

    public Response getInsuranceByCarRentalId(Long carRentalId)
    {
        try
        {
            List<InsuranceEntity> insuranceEntity = insuranceRepository.findByCarRentalId(carRentalId);
            if (!insuranceEntity.isEmpty())
            {
                return new Response(insuranceEntity);
            }
            else
            {
                log.warn("no insurance found with car with id:{}", carRentalId);
                return new Response(HttpStatus.NO_CONTENT, "no insurance found with such id: " + carRentalId);
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error occurred while getting insurance with id: " + carRentalId);
        }
    }

    public Response getInsuranceByCarId(String carId)
    {
        try
        {
            List<InsuranceEntity> insuranceEntity = insuranceRepository.findByCarId(carId);
            if (!insuranceEntity.isEmpty())
            {
                return new Response(insuranceEntity);
            }
            else
            {
                log.warn("no insurance found with car with id:{}", carId);
                return new Response(HttpStatus.NO_CONTENT, "no insurance found with such id: " + carId);
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error occurred while getting insurance with id: " + carId);
        }
    }


    public Response addInsurance(InsuranceDto insuranceDto)
    {
        try
        {
            UUID insuranceId = UUID.randomUUID();
            Response storeFileResponse = PhotoHelper.storeFiles(insuranceId.toString(), insuranceDto.getCarVideo(),
                    insuranceVideoDefaultPath, "insurance",false);

            if (!storeFileResponse.getStatus().equals(HttpStatus.OK))
            {
                return storeFileResponse;

            }
            else
            {
                InsuranceEntity insuranceEntity = new InsuranceEntity(insuranceId.toString(), insuranceDto.getCarRentalId(),
                        String.valueOf(storeFileResponse.getData()).replace("\\\\", "\\"));
                return new Response(insuranceRepository.save(insuranceEntity));
            }
        }
        catch (Exception e)
        {
            log.error("error while storing files for insurance:{}", e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, "error while storing files for insurance");

        }
    }
}
