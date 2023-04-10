package com.computershop.app.controller;

import com.computershop.app.model.Client;
import com.computershop.app.model.dto.ClientDTO;
import com.computershop.app.service.impl.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/client")
@Validated
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id){
        Client client = this.clientService.findById(id);
        return ResponseEntity.ok().body(client);
    }

    @GetMapping("/")
    public ResponseEntity<List<Client>> getAllClients(){
        List<Client> clients = this.clientService.findAll();
        return ResponseEntity.ok().body(clients);
    }

    @PostMapping("/new/")
    public ResponseEntity<Void> createClient(@RequestBody @Valid ClientDTO clientDTO){
        Client client = this.clientService.fromDTO(clientDTO);
        this.clientService.create(client);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/id").buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateClient(@RequestBody @Valid ClientDTO clientDTO, @PathVariable Long id){
        clientDTO.setId(id);
        Client client = this.clientService.fromDTO(clientDTO);
        client = this.clientService.update(client);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
       this.clientService.delete(id);
       return ResponseEntity.noContent().build();
    }

}
