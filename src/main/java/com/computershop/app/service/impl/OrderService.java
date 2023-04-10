package com.computershop.app.service.impl;

import com.computershop.app.model.Order;
import com.computershop.app.model.dto.OrderDTO;
import com.computershop.app.model.dto.request.OrderRequest;
import com.computershop.app.repository.OrderRepositoy;
import com.computershop.app.service.CrudService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class OrderService implements CrudService<Order> {

    @Autowired
    private OrderRepositoy orderRepositoy;

    @Autowired
    private ClientService clientService;

    @Override
    public Order findById(Long id) {
        Optional<Order> order = this.orderRepositoy.findById(id);
        return order.orElseThrow(()-> new RuntimeException("Order not found ID -> ("+id+")"));
    }

    @Override
    public ArrayList<Order> findAll() {
        return (ArrayList<Order>) this.orderRepositoy.findAll();
    }

    @Override
    @Transactional
    public Order create(Order order) {
        order.setId(null);
        return this.orderRepositoy.save(order);
    }

    @Override
    @Transactional
    public Order update(Order order) {
        Order newOrder = findById(order.getId());
        newOrder.setStatusOrder(order.getStatusOrder());
        return this.orderRepositoy.save(newOrder);
    }

    @Override
    public void delete(Long id) {
        try {
            this.orderRepositoy.deleteById(id);
        } catch (Exception ex){
            throw new RuntimeException("Order not found ID -> ("+id+")");
        }
    }

    public Order fromDTO(@Valid OrderDTO orderDTO){
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setStatusOrder(orderDTO.getStatusOrder());
        order.setCreationDate(orderDTO.getCreationDate());
        order.setClient(this.clientService.fromRequest(orderDTO.getClientResponse()));

        return order;
    }

    public Order fromRequest(@Valid OrderRequest orderRequest){
        Order order = new Order();
        order.setId(orderRequest.getId());
        return order;
    }
}
