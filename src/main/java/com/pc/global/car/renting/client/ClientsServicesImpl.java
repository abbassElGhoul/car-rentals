package com.pc.global.car.renting.client;

import com.pc.global.car.renting.carRentals.CarRentalsEntity;
import com.pc.global.car.renting.carRentals.CarRentalsService;
import com.pc.global.car.renting.client.dot.ClientDto;
import com.pc.global.car.renting.client.dot.ClientInfoDto;
import com.pc.global.car.renting.customeResponse.Response;
import com.pc.global.car.renting.helper.PhotoHelper;
import com.pc.global.car.renting.sponser.SponsorEntity;
import com.pc.global.car.renting.sponser.SponsorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class ClientsServicesImpl implements ClientsService
{

    private final ClientRepository clientRepository;
    private final CarRentalsService carRentalsService;
    private final SponsorService sponsorService;

    @Value("${photos.path.ids}")
    private String idImagesDefaultPath;

    private static ClientInfoDto getClientInfoRentingStatus(Optional<ClientEntity> client, Response carRentalsResponse, Response sponsorEntityResponse)
    {
        ClientInfoDto clientInfoDto = new ClientInfoDto(
                client.get().getName(),
                client.get().getPhoneNumber(),
                client.get().getAddress(),
                client.get().getFrontId(),
                client.get().getBackId(),
                null,
                null,
                false,
                null,
                null
        );

        if (carRentalsResponse.getStatus() == HttpStatus.OK)
        {
            Optional<CarRentalsEntity> carRentalsEntity = (Optional<CarRentalsEntity>) carRentalsResponse.getData();
            if (carRentalsEntity.isPresent())
            {
                clientInfoDto.setCurrentlyRenting(Boolean.TRUE);
                clientInfoDto.setRentalStartDate(carRentalsEntity.get().getRentalStartDate());
                clientInfoDto.setRentalEndDate(carRentalsEntity.get().getRentalEndDate());
            }
        }
        if (sponsorEntityResponse.getStatus() == HttpStatus.OK)
        {
            SponsorEntity sponsor = (SponsorEntity) sponsorEntityResponse.getData();
            clientInfoDto.setSponsorName(sponsor.getName());
            clientInfoDto.setSponsorPhoneNumber(sponsor.getPhoneNumber());
        }

        return clientInfoDto;
    }

    public Response getClients()
    {
        try
        {
            List<ClientEntity> clients = clientRepository.findAll();
            log.info("find all clients:{}", clients);
            if (!clients.isEmpty())
            {
                return new Response(clients);
            }
            else
            {
                log.warn("no data found in clients");
                return new Response(HttpStatus.NO_CONTENT, "No data found");
            }
        }
        catch (Exception e)
        {
            log.error("getClients error:{}", e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    public Response addClient(ClientDto clientDto)
    {
        try
        {
            UUID clientId = UUID.randomUUID();
            Response storeFileResponseFrontId = PhotoHelper.storeFiles(clientId.toString(), clientDto.getFrontId(), idImagesDefaultPath, "client", true);
            Response storeFileResponseBackId = PhotoHelper.storeFiles(clientId.toString(), clientDto.getBackId(), idImagesDefaultPath, "client", true);
            if (!storeFileResponseFrontId.getStatus().equals(HttpStatus.OK))
            {
                return storeFileResponseFrontId;
            }
            else if (!storeFileResponseBackId.getStatus().equals(HttpStatus.OK))
            {
                return storeFileResponseBackId;
            }
            else
            {
                ClientEntity client = new ClientEntity(String.valueOf(clientId), clientDto.getName(), clientDto.getPhoneNumber(),
                        String.valueOf(storeFileResponseFrontId.getData()).replace("\\\\", "\\"),
                        String.valueOf(storeFileResponseBackId.getData()).replace("\\\\", "\\"), clientDto.getTotalRentals(),
                        clientDto.getAddress(), clientDto.getCurrentlyRenting() != null && clientDto.getCurrentlyRenting(), clientDto.getSponsorId());

                return new Response(clientRepository.save(client));
            }
        }
        catch (DataIntegrityViolationException e)
        {
            log.error(e);
            return new Response(HttpStatus.BAD_REQUEST, "error while adding client (check sponsor id)");

        }
        catch (Exception e)
        {
            log.error("error while storing files:{}", e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, "error while storing files");
        }
    }

    public Response getClientInfo(String clientId)
    {
        try
        {
            SponsorEntity sponsorEntity = new SponsorEntity();
            Optional<ClientEntity> client = clientRepository.findById(clientId);
            if (client.isEmpty())
            {
                return new Response(HttpStatus.NO_CONTENT, "No data found");
            }

            Response carRentalsResponse = carRentalsService.getCarsByClientId(clientId);
            if (carRentalsResponse.getStatus().is5xxServerError())
            {
                return carRentalsResponse;
            }
            Response getSponsorResponse = sponsorService.getSponserById(client.get().getSponsorId());

            ClientInfoDto clientInfoDto = getClientInfoRentingStatus(client, carRentalsResponse, getSponsorResponse);

            log.info("Client info retrieved for clientId: {}", clientId);
            return new Response(clientInfoDto);
        }
        catch (Exception e)
        {
            log.error("Error occurred while getting client info for clientId: {}", clientId, e);
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while getting client info for clientId " + clientId);
        }
    }

}
