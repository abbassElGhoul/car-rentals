package com.pc.global.car.renting.admin;

import com.pc.global.car.renting.admin.dto.AdminDto;
import com.pc.global.car.renting.customeResponse.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Log4j2
public class AdminController
{

    private final AdminService adminService;

    @PostMapping("create-admin")
    public Response createAdmin(@RequestBody AdminDto adminDto)
    {
        try
        {
            return adminService.createAdmin(adminDto);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("login")
    public Response login(@RequestBody AdminDto adminDto)
    {
        try
        {
            return adminService.login(adminDto);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
