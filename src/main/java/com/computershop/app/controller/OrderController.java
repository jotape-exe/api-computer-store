package com.computershop.app.controller;

import com.computershop.app.model.Order;
import com.computershop.app.model.dto.request.OrderDTO;
import com.computershop.app.model.dto.request.OrderUpdateDTO;
import com.computershop.app.model.dto.response.OrderView;
import com.computershop.app.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
@Validated
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderView> getOrderById(@PathVariable Long id){
        Order order = this.orderService.findById(id);
        return ResponseEntity.ok().body(new OrderView(order));
    }

    @GetMapping("/")
    public ResponseEntity<List<OrderView>> getAllOrders(){
        List<OrderView> orders = this.orderService.findAll()
                .stream()
                .map(OrderView::new)
                .toList();
        return ResponseEntity.of(Optional.of(orders));
    }

    @PostMapping("/new")
    public ResponseEntity<Void> newOrder(@RequestBody OrderDTO orderDTO){
        Order order = this.orderService.create(orderDTO.toEntity());;
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateOrder(@RequestBody OrderUpdateDTO orderUpdateDTO, @PathVariable Long id){
        Order order = this.orderService.findById(id);
        Order orderToUpdate = orderUpdateDTO.toEntity(order);
        this.orderService.update(orderToUpdate);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        this.orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
