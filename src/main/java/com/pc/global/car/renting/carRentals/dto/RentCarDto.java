package com.pc.global.car.renting.carRentals.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentCarDto
{
    private String clientId;
    private String carId;
    private LocalDateTime rentalStartDate;
    private LocalDateTime rentalEndDate;
}
