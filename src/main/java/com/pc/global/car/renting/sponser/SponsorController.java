package com.pc.global.car.renting.sponser;

import com.pc.global.car.renting.customeResponse.Response;
import com.pc.global.car.renting.sponser.dto.SponsorDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.bytebuddy.asm.Advice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/sponsor")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Log4j2
public class SponsorController
{
    private final SponsorService sponsorService;

    @GetMapping("get-all-sponsors")
    public Response getAllSponsors()
    {
        try
        {
            return sponsorService.getAllSponsors();
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, "error while fetching sponsors");
        }
    }

    @PostMapping("add-sponsor")
    public Response addSponsor(@RequestBody SponsorDto sponsorDto)
    {
        try
        {
            return sponsorService.addSponsor(sponsorDto);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, "error while adding sponsors");
        }

    }
}
