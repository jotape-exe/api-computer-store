package com.computershop.app.controller;

import com.computershop.app.model.Costumer;
import com.computershop.app.model.dto.CostumerDTO;
import com.computershop.app.service.impl.CostumerService;
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
public class CostumerController {

    @Autowired
    private CostumerService costumerService;

    @GetMapping("/{id}")
    public ResponseEntity<Costumer> getClientById(@PathVariable Long id){
        Costumer costumer = this.costumerService.findById(id);
        return ResponseEntity.ok().body(costumer);
    }

    @GetMapping("/")
    public ResponseEntity<List<Costumer>> getAllClients(){
        List<Costumer> costumers = this.costumerService.findAll();
        return ResponseEntity.ok().body(costumers);
    }

    @PostMapping("/new/")
    public ResponseEntity<Void> createClient(@RequestBody @Valid CostumerDTO costumerDTO){
        Costumer costumer = this.costumerService.fromDTO(costumerDTO);
        this.costumerService.create(costumer);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/id").buildAndExpand(costumer.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateClient(@RequestBody @Valid CostumerDTO costumerDTO, @PathVariable Long id){
        costumerDTO.setId(id);
        Costumer costumer = this.costumerService.fromDTO(costumerDTO);
        costumer = this.costumerService.update(costumer);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
       this.costumerService.delete(id);
       return ResponseEntity.noContent().build();
    }

}
