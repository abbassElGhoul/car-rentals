package com.pc.global.car.renting.homePage;

import com.pc.global.car.renting.customeResponse.Response;
import org.springframework.stereotype.Service;

@Service
public interface HomePageService
{
    Response getHomePageInfo();
}
