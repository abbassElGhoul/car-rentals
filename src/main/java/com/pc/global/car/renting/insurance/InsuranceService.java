package com.pc.global.car.renting.insurance;

import com.pc.global.car.renting.customeResponse.Response;
import com.pc.global.car.renting.insurance.dto.InsuranceDto;
import org.springframework.stereotype.Service;

@Service
public interface InsuranceService
{

    Response getAllInsurance();
    Response getInsuranceById(Long insuranceId);

    Response getInsuranceByCarRentalId(Long carRentalId);

    Response getInsuranceByCarId(String carId);

    Response addInsurance(InsuranceDto insuranceDto);
}
