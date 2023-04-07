package com.computershop.app.service.impl;

import com.computershop.app.model.Address;
import com.computershop.app.model.Client;
import com.computershop.app.repository.AddressRepository;
import com.computershop.app.repository.ClientRepository;
import com.computershop.app.service.CrudService;
import com.computershop.app.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

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
    @Transactional
    public Client create(Client client) {
        client.setId(null);
        return saveClientCep(client);
    }

    @Override
    @Transactional
    public Client update(Client client) {
        Client newClient = findById(client.getId());
        newClient.setName(client.getName());
        newClient.setPhone(client.getPhone());
        newClient.setAddress(client.getAddress());

        return saveClientCep(newClient);
    }

    @Override
    public void delete(Long id) {
        try {
            this.clientRepository.deleteById(id);
        } catch (Exception ex){
            throw new RuntimeException("Client not Found! ID ( "+id+" )");
        }
    }

    public Client saveClientCep(Client client){
        String cep = client.getAddress().getCep();
        Address address = addressRepository.findById(cep).orElseGet(() -> {
            // Caso não exista, integrar com o ViaCEP e persistir o retorno.
            Address newAddress = viaCepService.consultarCep(cep);
            addressRepository.save(newAddress);
            return newAddress;
        });
        client.setAddress(address);
        // Inserir Cliente, vinculando o Endereco (novo ou existente).
        return this.clientRepository.save(client);
    }

}
