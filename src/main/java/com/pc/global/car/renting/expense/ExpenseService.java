package com.pc.global.car.renting.expense;

import com.pc.global.car.renting.customeResponse.Response;
import com.pc.global.car.renting.expense.dto.ExpenseDto;
import org.springframework.stereotype.Service;

@Service
public interface ExpenseService
{

    Response getAllExpenses();

    Response getExpensesDetails();

    Response addExpense(ExpenseDto expenseDto);
}
