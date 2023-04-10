package com.computershop.app.service.impl;

import com.computershop.app.model.Address;
import com.computershop.app.model.Client;
import com.computershop.app.model.dto.ClientDTO;
import com.computershop.app.model.dto.request.ClientRequest;
import com.computershop.app.repository.AddressRepository;
import com.computershop.app.repository.ClientRepository;
import com.computershop.app.service.CrudService;
import com.computershop.app.service.ViaCepService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class ClientService implements CrudService<Client> {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Autowired
    private AddressRepository addressRepository;
    @Override
    public Client findById(Long id) {
        Optional<Client> client = this.clientRepository.findById(id);
        return client.orElseThrow(() -> new RuntimeException("Client not Found! ID ( "+id+" )"));
    }

    @Override
    public ArrayList<Client> findAll() {
        return (ArrayList<Client>) this.clientRepository.findAll();
    }

    @Override
    @Transactional //Funciona se remover
    public Client create(Client client) {
        client.setId(null);
        return this.saveClientCep(client);
    }
    @Override
    @Transactional
    public Client update(Client client) {
        Client newClient = findById(client.getId());
        newClient.setName(client.getName());
        newClient.setPhone(client.getPhone());
        newClient.setAddress(client.getAddress());

        return this.saveClientCep(newClient);
    }

    @Override
    public void delete(Long id) {
        try {
            this.clientRepository.deleteById(id);
        } catch (Exception ex){
            throw new RuntimeException("Client not Found! ID ( "+id+" )");
        }
    }

    private Client saveClientCep(Client client){
        CompletableFuture<Address> addressFuture = CompletableFuture.supplyAsync(()->{
            String cep = client.getAddress().getCep();
            return addressRepository.findById(cep).orElseGet(()->{
                Address newAddress = viaCepService.consultarCep(cep);
                addressRepository.save(newAddress);
                return newAddress;
            });
        });
        client.setAddress(addressFuture.join());
        return this.clientRepository.save(client);
    }


    public Client fromDTO(@Valid ClientDTO clientDTO){
        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setName(clientDTO.getName());
        client.setPhone(clientDTO.getPhone());
        client.setAddress(clientDTO.getAddress());
        return client;
    }

    public Client fromRequest(@Valid ClientRequest clientRequest){
        Client client = new Client();
        client.setId(clientRequest.getId());
        return client;
    }



}
