package com.pc.global.car.renting.insurance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceDto
{
    private Long carRentalId;
    private MultipartFile carVideo;
}
