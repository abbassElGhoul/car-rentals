package com.pc.global.car.renting.client;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "client")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientEntity
{
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "front_id")
    private String frontId;
    @Column(name = "back_id")
    private String backId;
    @Column(name = "total_rentals")
    private int totalRentals;
    @Column(name = "address")
    private String address;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "currently_renting")
    private Boolean currentlyRenting;
    @Column(name = "sponsor_id")
    private Long sponsorId;

    public ClientEntity(String id, String name, String phoneNumber, String frontId, String backId,
                        int totalRentals, String address, boolean currentlyRenting, Long sponsorId)
    {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.frontId = frontId;
        this.backId = backId;
        this.totalRentals = totalRentals;
        this.address = address;
        this.currentlyRenting = currentlyRenting;
        this.sponsorId = sponsorId;
    }

    @PrePersist
    public void prePersist()
    {
        this.createdDate = LocalDateTime.now();
    }
}
