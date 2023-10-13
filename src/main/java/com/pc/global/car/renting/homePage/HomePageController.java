package com.pc.global.car.renting.homePage;

import com.pc.global.car.renting.customeResponse.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Random;

@RestController
@RequestMapping("api/v1/home-page")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class HomePageController
{
    private final HomePageService homePageService;

    @GetMapping("get-home-page")
    public Response getHomePage()
    {
        try
        {
            return homePageService.getHomePageInfo();
        }
        catch (Exception e)
        {
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("send-sms")
    public SendSmsResponse sendSms(@RequestBody MessageContentDto messageContentDto)
    {
        try
        {
            Random random = new Random();
            int randomNumber = random.nextInt();
            if (randomNumber % 2 == 0)
            {
                return new SendSmsResponse("M000001020-000000000046", "bd353a7a-283d-4b1e-8091-06515c8a31f7",
                        messageContentDto.getMessage(), messageContentDto.getMsisdn(), 1, "OK", LocalDateTime.now(), "CTR-4545678764567", null);
            }
            else
            {
                return new SendSmsResponse("M000001020-000000000046", "bd353a7a-283d-4b1e-8091-06515c8a31f7",
                        messageContentDto.getMessage(), messageContentDto.getMsisdn(), 1, "ALREADY_EXISTS", LocalDateTime.now(), "CTR-4545678764567", null);
            }
        }
        catch (Exception e)
        {
            return new SendSmsResponse();
        }
    }
}