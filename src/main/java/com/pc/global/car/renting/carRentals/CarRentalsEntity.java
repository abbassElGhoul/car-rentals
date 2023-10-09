package com.pc.global.car.renting.carRentals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "car_rentals")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRentalsEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "client_id")
    private String clientId;
    @Column(name = "car_id")
    private String carId;
    @Column(name = "rental_start_Date")
    private LocalDateTime rentalStartDate;
    @Column(name = "rental_end_date")
    private LocalDateTime rentalEndDate;

    public CarRentalsEntity(String clientId, String carId, LocalDateTime rentalStartDate, LocalDateTime rentalEndDate)
    {
        this.clientId = clientId;
        this.carId = carId;
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
    }
}
