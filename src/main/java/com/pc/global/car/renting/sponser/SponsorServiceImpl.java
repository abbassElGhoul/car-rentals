package com.pc.global.car.renting.sponser;


import com.pc.global.car.renting.customeResponse.Response;
import com.pc.global.car.renting.sponser.dto.SponsorDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class SponsorServiceImpl implements SponsorService
{
    private final SponsorRepository sponsorRepository;

    public Response getAllSponsors()
    {
        try
        {
            List<SponsorEntity> sponsorEntity = sponsorRepository.findAll();
            log.info("getAllSponsors() sponsorEntity:{}", sponsorEntity);
            return new Response(sponsorEntity);

        }
        catch (Exception e)
        {
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, "error while fetching sponsors");
        }
    }

    public Response addSponsor(SponsorDto sponsorDto)
    {
        try
        {
            SponsorEntity sponsorEntity = sponsorRepository.save(new SponsorEntity(sponsorDto.getName(),
                    sponsorDto.getPhoneNumber()));
            return new Response(sponsorEntity);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, "error while adding sponsor");
        }
    }
}
