package com.pc.global.car.renting.sponser;

import com.pc.global.car.renting.customeResponse.Response;
import com.pc.global.car.renting.sponser.dto.SponsorDto;
import org.springframework.stereotype.Service;

@Service
public interface SponsorService
{
    Response getAllSponsors();

    Response addSponsor(SponsorDto sponsorDto);
}
