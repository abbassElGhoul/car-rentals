package com.pc.global.car.renting.insurance;

import com.pc.global.car.renting.customeResponse.Response;
import com.pc.global.car.renting.insurance.dto.InsuranceDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/insurance")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Log4j2
public class InsuranceController
{
    private final InsuranceService insuranceService;

    @GetMapping("get-all-insurance")
    public Response getAllInsurance()
    {
        try
        {
            return insuranceService.getAllInsurance();
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("get-insurance")
    public Response getInsuranceById(@RequestParam Long insuranceId)
    {
        try
        {
            return insuranceService.getInsuranceById(insuranceId);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("get-insurance-by-car-rental-id")
    public Response getInsuranceByCarId(@RequestParam Long carRentalId)
    {
        try
        {
            return insuranceService.getInsuranceByCarRentalId(carRentalId);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("get-insurance-by-car-id")
    public Response getInsuranceByCarId(@RequestParam String carId)
    {
        try
        {
            return insuranceService.getInsuranceByCarId(carId);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping(value = "add-insurance", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    private Response addInsurance(@ModelAttribute InsuranceDto insuranceDto)
    {
        try
        {
            return insuranceService.addInsurance(insuranceDto);
        }
        catch (Exception e)
        {
            log.error("error adding car insurance:{}", e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
