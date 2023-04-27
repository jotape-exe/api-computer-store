package com.computershop.app.controller;

import com.computershop.app.model.Customer;
import com.computershop.app.model.dto.request.CustomerDTO;
import com.computershop.app.model.dto.response.CustomerView;
import com.computershop.app.model.dto.response.CustomerViewList;
import com.computershop.app.service.impl.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
@Validated
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<CustomerView> getClientById(@PathVariable Long id){
        Customer customer = this.customerService.findById(id);
        return ResponseEntity.ok().body(new CustomerView(customer));
    }

    @GetMapping("/")
    public ResponseEntity<List<CustomerViewList>> getAllClients(){
        List<CustomerViewList> customers = this.customerService.findAll()
                .stream()
                .map(CustomerViewList::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body((customers));
    }

    @PostMapping("/new/")
    public ResponseEntity<Void> createClient(@RequestBody @Valid CustomerDTO customerDTO){
        Customer customer = this.customerService.create(customerDTO.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //UPDATE????
    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateClient(@RequestBody @Valid CustomerDTO customerDTO, @PathVariable Long id){
        Customer customer = this.customerService.findById(id);
        Customer customerToUpdate = customerDTO.toEntity(customer);
        this.customerService.update(customerToUpdate);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
       this.customerService.delete(id);
       return ResponseEntity.noContent().build();
    }

}
