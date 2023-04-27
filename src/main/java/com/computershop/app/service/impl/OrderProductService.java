package com.computershop.app.service.impl;

import com.computershop.app.model.OrderProduct;
import com.computershop.app.repository.OrderProductRepository;
import com.computershop.app.service.exceptions.DataBindingViolationException;
import com.computershop.app.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class OrderProductService {

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    public OrderProduct findById(Long id) {
        Optional<OrderProduct> orderProduct = this.orderProductRepository.findById(id);
        return orderProduct.orElseThrow(()-> new ObjectNotFoundException("Product Not Found! ID -> "+id));
    }

    public ArrayList<OrderProduct> findAll() {
        return (ArrayList<OrderProduct>) this.orderProductRepository.findAll();
    }


    public OrderProduct create(OrderProduct orderProduct) {
        orderProduct.setId(null);
        return this.orderProductRepository.save(orderProduct);
    }


    public void delete(Long id) {
        OrderProduct orderProduct = this.findById(id);
        try {
            this.orderProductRepository.delete(orderProduct);
        } catch (Exception ex){
            throw new DataBindingViolationException("Cannot delete, the entity have relationships");
        }
    }
}
