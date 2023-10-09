package com.pc.global.car.renting.client.dot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto
{
    private String name;
    private String phoneNumber;
    private MultipartFile frontId;
    private MultipartFile backId;
    private int totalRentals;
    private String address;
    private Boolean currentlyRenting;
    private Long sponsorId;
//    private String created_date;
}
