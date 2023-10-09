package com.pc.global.car.renting.client;

import com.pc.global.car.renting.client.dot.ClientDto;
import com.pc.global.car.renting.customeResponse.Response;
import org.springframework.stereotype.Service;

@Service
public interface ClientsService
{
    Response getClients();

    Response addClient(ClientDto clientDto);

    Response getClientInfo(String clientId);
}
