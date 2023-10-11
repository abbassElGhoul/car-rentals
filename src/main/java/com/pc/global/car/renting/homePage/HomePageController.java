package com.pc.global.car.renting.homePage;

import com.pc.global.car.renting.customeResponse.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}