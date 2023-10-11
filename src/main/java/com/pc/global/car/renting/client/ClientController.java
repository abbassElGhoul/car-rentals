package com.pc.global.car.renting.client;

import com.pc.global.car.renting.client.dot.ClientDto;
import com.pc.global.car.renting.customeResponse.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/client")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ClientController
{

    private final ClientsService clientsService;

    @GetMapping("get-all-clients")
    public Response getClients()
    {
        try
        {
            return clientsService.getClients();
        }
        catch (Exception e)
        {
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

        }
    }

    @PostMapping(value = "add-client", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Response addClient(@ModelAttribute ClientDto clientDto)
    {
        try
        {
            return clientsService.addClient(clientDto);
        }
        catch (Exception e)
        {
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

        }
    }

    @GetMapping("get-client-info")
    public Response getClientInfo(@RequestParam String clientId)
    {
        try
        {
            return clientsService.getClientInfo(clientId);
        }
        catch (Exception e)
        {
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

        }
    }

}
