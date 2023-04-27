package com.computershop.app.service.impl;

import com.computershop.app.model.Order;
import com.computershop.app.repository.OrderRepositoy;
import com.computershop.app.service.CrudService;
import com.computershop.app.service.exceptions.DataBindingViolationException;
import com.computershop.app.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class OrderService implements CrudService<Order>{

    @Autowired
    private OrderRepositoy orderRepositoy;

    @Autowired
    private CustomerService customerService;

    @Override
    public Order findById(Long id) {
        Optional<Order> order = this.orderRepositoy.findById(id);
        return order.orElseThrow(()-> new ObjectNotFoundException("Product Not Found! ID -> "+id));
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
        Order order = this.findById(id);
        try {
            this.orderRepositoy.delete(order);
        } catch (Exception ex){
            throw new DataBindingViolationException("Cannot delete, the entity have relationships");
        }
    }
}
