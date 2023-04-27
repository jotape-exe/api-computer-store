package com.computershop.app.controller;

import com.computershop.app.model.Order;
import com.computershop.app.model.dto.OrderDTO;
import com.computershop.app.model.dto.OrderUpdateDTO;
import com.computershop.app.model.dto.response.OrderView;
import com.computershop.app.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(orders);
    }

    @PostMapping("/new")
    public ResponseEntity<OrderView> newOrder(@RequestBody OrderDTO orderDTO){
        Order order = this.orderService.create(orderDTO.toEntity());;
        return ResponseEntity.status(HttpStatus.CREATED).body(new OrderView(order));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrderView> updateOrder(@RequestBody OrderUpdateDTO orderUpdateDTO, @PathVariable Long id){
        Order order = this.orderService.findById(id);
        Order orderToUpdate = orderUpdateDTO.toENtity(order);
        Order orderUpdated = this.orderService.update(orderToUpdate);
        return ResponseEntity.ok().body(new OrderView(orderUpdated));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        this.orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
