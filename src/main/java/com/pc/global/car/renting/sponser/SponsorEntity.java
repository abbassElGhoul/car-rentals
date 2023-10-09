package com.pc.global.car.renting.sponser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "sponsor")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SponsorEntity
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;

    public SponsorEntity(String name, String phoneNumber)
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
