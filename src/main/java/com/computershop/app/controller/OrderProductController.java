package com.computershop.app.controller;

import com.computershop.app.model.OrderProduct;
import com.computershop.app.model.dto.request.OrderProductDTO;
import com.computershop.app.model.dto.response.OrderProductView;
import com.computershop.app.service.impl.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/item-order")
@Validated
public class OrderProductController {
    @Autowired
    private OrderProductService orderProductService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderProductView> getOrderProductById(@PathVariable Long id){
        OrderProduct orderProduct = this.orderProductService.findById(id);
        return ResponseEntity.ok().body(new OrderProductView(orderProduct));
    }

    @GetMapping("/")
    public ResponseEntity<List<OrderProductView>> getAllOrderProducts(){
        List<OrderProductView> orderProducts = this.orderProductService.findAll()
                .stream()
                .map(OrderProductView::new)
                .toList();
        return ResponseEntity.of(Optional.of(orderProducts));
    }

    @PostMapping("/new")
    public ResponseEntity<Void> newOrderProduct(@RequestBody OrderProductDTO orderProductDTO){
        OrderProduct orderProduct = this.orderProductService.create(orderProductDTO.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        this.orderProductService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
