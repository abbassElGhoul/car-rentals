package com.pc.global.car.renting.expense.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalExpensesDto
{
    private int total;
    private int income;
    private int fees;
}
