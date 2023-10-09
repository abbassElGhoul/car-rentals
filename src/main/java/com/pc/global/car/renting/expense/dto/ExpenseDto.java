package com.pc.global.car.renting.expense.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDto
{
    private Integer type;
    private String description;
    private Integer amount;
}
