package com.computershop.app.controller;

import com.computershop.app.model.Customer;
import com.computershop.app.model.dto.CustomerDTO;
import com.computershop.app.service.impl.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/costumer")
@Validated
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getClientById(@PathVariable Long id){
        Customer customer = this.customerService.findById(id);
        return ResponseEntity.ok().body(customer);
    }

    @GetMapping("/")
    public ResponseEntity<List<Customer>> getAllClients(){
        List<Customer> customers = this.customerService.findAll();
        return ResponseEntity.ok().body(customers);
    }

    @PostMapping("/new/")
    public ResponseEntity<Void> createClient(@RequestBody @Valid CustomerDTO customerDTO){
        Customer customer = this.customerService.fromDTO(customerDTO);
        this.customerService.create(customer);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/id").buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateClient(@RequestBody @Valid CustomerDTO customerDTO, @PathVariable Long id){
        customerDTO.setId(id);
        Customer customer = this.customerService.fromDTO(customerDTO);
        customer = this.customerService.update(customer);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
       this.customerService.delete(id);
       return ResponseEntity.noContent().build();
    }

}
