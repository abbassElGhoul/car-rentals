package com.pc.global.car.renting.expense;

import com.pc.global.car.renting.customeResponse.Response;
import com.pc.global.car.renting.expense.dto.ExpenseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/expenses")
@RequiredArgsConstructor
public class ExpenseController
{
    private final ExpenseService expenseService;


    @GetMapping("get-all-expenses")
    public Response getAllExpenses()
    {
        try
        {
            return expenseService.getAllExpenses();
        }
        catch (Exception e)
        {
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @GetMapping("get-expenses-details")
    public Response getExpensesDetails()
    {
        try
        {
            return expenseService.getExpensesDetails();
        }
        catch (Exception e)
        {
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("add-expense")
    public Response addExpense(@RequestBody ExpenseDto expenseDto)
    {
        try
        {
            return expenseService.addExpense(expenseDto);
        }
        catch (Exception e)
        {
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
