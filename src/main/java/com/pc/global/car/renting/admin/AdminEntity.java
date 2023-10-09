package com.pc.global.car.renting.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "admin")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminEntity
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="username")
    private String username;
    @Column(name="password")
    private String password;

    public AdminEntity(String username, String password)
    {
        this.username = username;
        this.password = password;
    }
}
