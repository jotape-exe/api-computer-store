package com.computershop.app.service.impl;

import com.computershop.app.model.Address;
import com.computershop.app.model.Customer;
import com.computershop.app.repository.AddressRepository;
import com.computershop.app.repository.CostumerRepository;
import com.computershop.app.service.CrudService;
import com.computershop.app.service.ViaCepService;
import com.computershop.app.service.exceptions.DataBindingViolationException;
import com.computershop.app.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class CustomerService implements CrudService<Customer>{

    @Autowired
    private CostumerRepository costumerRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Autowired
    private AddressRepository addressRepository;
    @Override
    public Customer findById(Long id) {
        Optional<Customer> client = this.costumerRepository.findById(id);
        return client.orElseThrow(() -> new ObjectNotFoundException("Product Not Found! ID -> "+id));
    }

    @Override
    public ArrayList<Customer> findAll() {
        return (ArrayList<Customer>) this.costumerRepository.findAll();
    }

    @Override
    @Transactional //Funciona se remover
    public Customer create(Customer customer) {
        return this.saveClientCep(customer);
    }
    @Override
    @Transactional
    public Customer update(Customer customer) {
        Customer newCustomer = findById(customer.getId());
        newCustomer.setName(customer.getName());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setAddress(customer.getAddress());

        return this.saveClientCep(newCustomer);
    }

    @Override
    public void delete(Long id) {
        Customer customer = this.findById(id);
        try {
            this.costumerRepository.delete(customer);
        } catch (Exception ex){
            throw new DataBindingViolationException("Cannot delete, the entity have relationships");
        }
    }

    private Customer saveClientCep(Customer customer){
        CompletableFuture<Address> addressFuture = CompletableFuture.supplyAsync(()->{
            String cep = customer.getAddress().getCep();
            return addressRepository.findById(cep).orElseGet(()->{
                Address newAddress = viaCepService.consultarCep(cep);
                addressRepository.save(newAddress);
                return newAddress;
            });
        });
        customer.setAddress(addressFuture.join());
        return this.costumerRepository.save(customer);
    }
}
