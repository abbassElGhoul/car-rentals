package com.pc.global.car.renting.homePage;

import com.pc.global.car.renting.car.CarRepository;
import com.pc.global.car.renting.client.ClientRepository;
import com.pc.global.car.renting.customeResponse.Response;
import com.pc.global.car.renting.expense.ExpenseServiceImpl;
import com.pc.global.car.renting.expense.dto.TotalExpensesDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class HomePageServiceImpl implements HomePageService
{

    private final ClientRepository clientRepository;
    private final CarRepository carRepository;
    private final ExpenseServiceImpl expenseServiceImpl;

    public Response getHomePageInfo()
    {
        log.info("get home page details");
        TotalExpensesDto totalExpensesDto = (TotalExpensesDto) expenseServiceImpl.getExpensesDetails().getData();
        HomePageDto homePageDto = new HomePageDto(Math.toIntExact(clientRepository.count()),
                Math.toIntExact(carRepository.count()), totalExpensesDto.getIncome(), totalExpensesDto.getFees());
        log.info("homePageDto:{}",homePageDto);
        return new Response(homePageDto);
    }


}
