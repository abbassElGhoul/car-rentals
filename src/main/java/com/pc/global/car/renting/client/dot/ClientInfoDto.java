package com.pc.global.car.renting.client.dot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientInfoDto
{
    private String clientName;
    private String clientPhoneNumber;
    private String clientAddress;
    private String frontId;
    private String backId;
    private Boolean currentlyRenting;
    private LocalDateTime rentalStartDate;
    private LocalDateTime rentalEndDate;

}
