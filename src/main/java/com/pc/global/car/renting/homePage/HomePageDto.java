package com.pc.global.car.renting.homePage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomePageDto
{
    private Integer totalClients;
    private Integer totalCars;
    private Integer totalMoneySpent;
    private Integer totalMoneyGained;

}
