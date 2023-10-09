package com.pc.global.car.renting.admin;

import com.pc.global.car.renting.admin.dto.AdminDto;
import com.pc.global.car.renting.customeResponse.Response;
import org.springframework.stereotype.Service;

@Service
public interface AdminService
{
    Response createAdmin(AdminDto adminDto);

    Response login(AdminDto adminDto);
}
