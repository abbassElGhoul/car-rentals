package com.pc.global.car.renting.expense;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "expense")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseEntity
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type")
    private Integer type;
    @Column(name = "description")
    private String description;
    @Column(name = "amount")
    private Integer amount;

    public ExpenseEntity(Integer type, String description, Integer amount)
    {
        this.type = type;
        this.description = description;
        this.amount = amount;
    }
}
