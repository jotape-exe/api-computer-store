package com.computershop.app.service.impl;

import com.computershop.app.model.Address;
import com.computershop.app.model.Costumer;
import com.computershop.app.model.dto.CostumerDTO;
import com.computershop.app.model.dto.request.AddressRequest;
import com.computershop.app.model.dto.request.CostumerRequest;
import com.computershop.app.repository.AddressRepository;
import com.computershop.app.repository.CostumerRepository;
import com.computershop.app.service.ConvertService;
import com.computershop.app.service.CrudService;
import com.computershop.app.service.ViaCepService;
import com.computershop.app.service.exceptions.DataBindingViolationException;
import com.computershop.app.service.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class CostumerService implements CrudService<Costumer>, ConvertService<Costumer, CostumerDTO, CostumerRequest> {

    @Autowired
    private CostumerRepository costumerRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Autowired
    private AddressRepository addressRepository;
    @Override
    public Costumer findById(Long id) {
        Optional<Costumer> client = this.costumerRepository.findById(id);
        return client.orElseThrow(() -> new ObjectNotFoundException("Product Not Found! ID -> "+id));
    }

    @Override
    public ArrayList<Costumer> findAll() {
        return (ArrayList<Costumer>) this.costumerRepository.findAll();
    }

    @Override
    @Transactional //Funciona se remover
    public Costumer create(Costumer costumer) {
        costumer.setId(null);
        return this.saveClientCep(costumer);
    }
    @Override
    @Transactional
    public Costumer update(Costumer costumer) {
        Costumer newCostumer = findById(costumer.getId());
        newCostumer.setName(costumer.getName());
        newCostumer.setPhone(costumer.getPhone());
        newCostumer.setAddress(costumer.getAddress());

        return this.saveClientCep(newCostumer);
    }

    @Override
    public void delete(Long id) {
        try {
            this.costumerRepository.deleteById(id);
        } catch (Exception ex){
            throw new DataBindingViolationException("Cannot delete, the entity have relationships");
        }
    }

    private Costumer saveClientCep(Costumer costumer){
        CompletableFuture<Address> addressFuture = CompletableFuture.supplyAsync(()->{
            String cep = costumer.getAddress().getCep();
            return addressRepository.findById(cep).orElseGet(()->{
                Address newAddress = viaCepService.consultarCep(cep);
                addressRepository.save(newAddress);
                return newAddress;
            });
        });
        costumer.setAddress(addressFuture.join());
        return this.costumerRepository.save(costumer);
    }


    @Override
    public Costumer fromDTO(@Valid CostumerDTO costumerDTO){
        Costumer costumer = new Costumer();
        costumer.setId(costumerDTO.getId());
        costumer.setName(costumerDTO.getName());
        costumer.setPhone(costumerDTO.getPhone());
        costumer.setAddress(fromRequest(costumerDTO.getAddressRequest()));
        return costumer;
    }

    @Override
    public Costumer fromRequest(@Valid CostumerRequest costumerRequest){
        Costumer costumer = new Costumer();
        costumer.setId(costumerRequest.getId());
        return costumer;
    }

    public Address fromRequest(@Valid AddressRequest addressRequest){
        Address address = new Address();
        address.setCep(addressRequest.getCep());
        return address;
    }



}
