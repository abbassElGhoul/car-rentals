package com.pc.global.car.renting.car;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "car")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarEntity
{
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "license_plate")
    private String licensePlate;
    @Column(name ="make_model")
    private String makeModel;
    @Column(name = "renting_status")
    private Boolean rentingStatus;
    @Column(name = "car_image")
    private String carImage;
}
