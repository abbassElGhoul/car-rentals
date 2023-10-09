package com.pc.global.car.renting.admin;

import com.pc.global.car.renting.admin.dto.AdminDto;
import com.pc.global.car.renting.customeResponse.Response;
import com.pc.global.car.renting.helper.EncryptionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService
{

    private final AdminRepository adminRepository;

    private final EncryptionUtils encryptionUtils;

    public Response createAdmin(AdminDto adminDto)
    {
        try
        {
            Response encryptionResponse = encryptionUtils.encrypt(adminDto.getPassword());
            if (encryptionResponse.getStatus().equals(HttpStatus.OK))
            {
                return new Response(adminRepository.save(new AdminEntity(adminDto.getUsername(), encryptionResponse.getData().toString())));
            }
            else
            {
                return encryptionResponse;
            }
        }
        catch (DataIntegrityViolationException e)
        {
            log.error("username already in use, error while creating admin:{}", e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, "username already in use");
        }
        catch (Exception e)
        {
            log.error("error while creating admin:{}", e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, "error while creating admin");

        }
    }

    public Response login(AdminDto adminDto)
    {
        Optional<AdminEntity> adminEntity = adminRepository.findByUsername(adminDto.getUsername());
        if (adminEntity.isPresent())
        {
            Response decryptionResponse = encryptionUtils.decrypt(adminEntity.get().getPassword());
            if (decryptionResponse.getStatus().equals(HttpStatus.OK))
            {
                if (String.valueOf(decryptionResponse.getData()).equals(adminDto.getPassword()))
                {
                    return new Response(adminDto.getUsername());
                }
                else
                {
                    return new Response(HttpStatus.UNAUTHORIZED, "wrong password");
                }
            }
            else
            {
                return decryptionResponse;
            }
        }
        else
        {
            return new Response(HttpStatus.UNAUTHORIZED, "wrong username");
        }

    }


}
