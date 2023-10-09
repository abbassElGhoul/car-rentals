package com.pc.global.car.renting.expense;

import com.pc.global.car.renting.customeResponse.Response;
import com.pc.global.car.renting.expense.dto.ExpenseDto;
import com.pc.global.car.renting.expense.dto.TotalExpensesDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ExpenseServiceImpl implements ExpenseService
{

    private final ExpensesRepository expensesRepository;

    public Response getAllExpenses()
    {
        try
        {
            List<ExpenseEntity> expenses = expensesRepository.findAll();
            log.info("find all expenses:{}", expenses);
            if (!expenses.isEmpty())
            {
                return new Response(expenses);
            }
            else
            {
                log.info("no data found in expenses");
                return new Response(HttpStatus.NO_CONTENT, "No data found");

            }
        }
        catch (Exception e)
        {
            log.error("getAllExpenses error:{}", e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public Response getExpensesDetails()
    {
        try
        {

            List<ExpenseEntity> expenses = expensesRepository.findAll();
            log.info("get expenses details:{}", expenses);
            TotalExpensesDto totalExpensesDto = getTotalExpensesDto(expenses);
            log.info("totalExpensesDto:{}", totalExpensesDto);
            return new Response(totalExpensesDto);
        }
        catch (Exception e)
        {
            log.info(e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    private static TotalExpensesDto getTotalExpensesDto(List<ExpenseEntity> expenses)
    {
        int total = 0;
        int income = 0;
        int fees = 0;
        for (ExpenseEntity expense : expenses)
        {
            if (expense.getType() == 0)
            {
                fees += expense.getAmount();
                total -= expense.getAmount();
            }
            else if (expense.getType() == 1)
            {
                income += expense.getAmount();
                total += expense.getAmount();
            }
        }
        TotalExpensesDto totalExpensesDto = new TotalExpensesDto(total, income, fees);
        return totalExpensesDto;
    }

    public Response addExpense(ExpenseDto expenseDto)
    {
        try
        {
            log.info("trying to add expense:{}", expenseDto);
            if (expenseDto.getType().equals(0) || expenseDto.getType().equals(1))
            {
                ExpenseEntity expenseEntity = expensesRepository.save(new ExpenseEntity(expenseDto.getType(),
                        expenseDto.getDescription(), expenseDto.getAmount()));
                return new Response(expenseEntity);
            }
            else
            {
                return new Response(HttpStatus.BAD_REQUEST, "expense type should be either 0" +
                        " for fees or 1 for income");
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
