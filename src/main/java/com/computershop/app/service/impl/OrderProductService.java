package com.computershop.app.service.impl;

import com.computershop.app.model.OrderProduct;
import com.computershop.app.repository.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class OrderProductService {

    @Autowired
    private OrderProductRepository orderProductRepository;

    public OrderProduct findById(Long id) {
        Optional<OrderProduct> orderProduct = this.orderProductRepository.findById(id);
        return orderProduct.orElseThrow(()-> new RuntimeException("Order item not found ID -> ("+id+")"));
    }

    public ArrayList<OrderProduct> findAll() {
        return (ArrayList<OrderProduct>) this.orderProductRepository.findAll();
    }


    public OrderProduct create(OrderProduct orderProduct) {
        orderProduct.setId(null);
        return this.orderProductRepository.save(orderProduct);
    }


    public void delete(Long id) {
        try {
            this.orderProductRepository.deleteById(id);
        } catch (Exception ex){
            throw new RuntimeException("Order item not found ID -> ("+id+")");
        }
    }
}
