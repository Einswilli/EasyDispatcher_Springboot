package com.tft.easyfuturedispatch.core.controller;
import com.tft.easyfuturedispatch.core.dao.ClientRepository;
import com.tft.easyfuturedispatch.core.entitie.Client;
import com.tft.easyfuturedispatch.core.exception.ResourceNotFoundException;
import com.tft.easyfuturedispatch.core.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/easyfuturedispatch/api/v1")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("/client")
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/client/type/{clientType}")
    public List<Client> getClientsByType(@PathVariable(value = "clientType")String clientType) {
        return clientRepository.getClientsByType(clientType);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable(value = "id") Long clientId)
            throws ResourceNotFoundException {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + clientId));
        return ResponseEntity.ok().body(client);
    }

    @PostMapping("/client")
    public Client createClient( @RequestBody Client client) {
        client.setId(sequenceGeneratorService.generateSequence(Client.SEQUENCE_NAME));
        return clientRepository.save(client);
    }

    @PutMapping("/client/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable(value = "id") Long clientId,
                                                @RequestBody Client clientDetails) throws ResourceNotFoundException {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + clientId));

        //client.setClientType(clientDetails.getClientType());
        client.setClientName(clientDetails.getClientName());
        client.setClientAdresseCountry(clientDetails.getClientAdresseCountry());
        client.setClientPhoneNumber(clientDetails.getClientPhoneNumber());
        client.setClientAdresseMunicipalNumber(clientDetails.getClientAdresseMunicipalNumber());
        client.setClientAdresseStreetName(clientDetails.getClientAdresseStreetName());
        client.setClientAdresseCity(clientDetails.getClientAdresseCity());
        client.setClientAdresseCountry(clientDetails.getClientAdresseCountry());
        client.setClientAdresseComplete(clientDetails.getClientAdresseComplete());
        // client.setClientExpediteur(clientDetails.getClientExpediteur());
        // client.setClientDestinataire(clientDetails.getClientDestinataire());
        // client.setClientPayeur(clientDetails.getClientPayeur());

        final Client updatedClient = clientRepository.save(client);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/client/{id}")
    public Map<String, Boolean> deleteClient(@PathVariable(value = "id") Long clientId)
            throws ResourceNotFoundException {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + clientId));

        clientRepository.delete(client);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
