package com.computershop.app.controller;

import com.computershop.app.model.OrderProduct;
import com.computershop.app.model.dto.OrderProductDTO;
import com.computershop.app.service.impl.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/item-order")
@Validated
public class OrderProductController {
    @Autowired
    private OrderProductService orderProductService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderProduct> getOrderProductById(@PathVariable Long id){
        OrderProduct orderProduct = this.orderProductService.findById(id);
        return ResponseEntity.ok().body(orderProduct);
    }

    @GetMapping("/")
    public ResponseEntity<List<OrderProduct>> getAllOrderProducts(){
        List<OrderProduct> orderProducts = this.orderProductService.findAll();
        return ResponseEntity.ok().body(orderProducts);
    }

    @PostMapping("/new")
    public ResponseEntity<Void> newOrderproduct(@RequestBody OrderProductDTO orderProductDTO){
        OrderProduct orderProduct = this.orderProductService.fromDTO(orderProductDTO);
        this.orderProductService.create(orderProduct);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/id").buildAndExpand(orderProduct.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        this.orderProductService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
