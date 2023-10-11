package com.pc.global.car.renting.carRentals.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDetailsDto
{
    private String clientName;
    private String sponsorName;
    private String sponsorPhoneNumber;
    private LocalDateTime rentalStartDate;
    private LocalDateTime rentalEndDate;
}
