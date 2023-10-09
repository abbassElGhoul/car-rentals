package com.pc.global.car.renting.expense;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesRepository extends JpaRepository<ExpenseEntity, Long>
{
}
