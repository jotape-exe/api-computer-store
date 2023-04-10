package com.computershop.app.controller;

import com.computershop.app.model.Order;
import com.computershop.app.model.dto.OrderDTO;
import com.computershop.app.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
@Validated
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id){
        Order order = this.orderService.findById(id);
        return ResponseEntity.ok().body(order);
    }

    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> orders = this.orderService.findAll();
        return ResponseEntity.ok().body(orders);
    }

    @PostMapping("/new")
    public ResponseEntity<Void> newOrder(@RequestBody OrderDTO orderDTO){
        Order order = this.orderService.fromDTO(orderDTO);
        this.orderService.create(order);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/id").buildAndExpand(order.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateOrder(@RequestBody OrderDTO orderDTO, @PathVariable Long id){
        orderDTO.setId(id);
        Order order = this.orderService.fromDTO(orderDTO);
        order = this.orderService.update(order);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        this.orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
