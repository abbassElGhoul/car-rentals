package com.pc.global.car.renting.insurance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "insurance")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceEntity
{
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "car_rental_id")
    private Long carRentalId;
    @Column(name = "car_video")
    private String carVideo;

}
