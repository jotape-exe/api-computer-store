package com.computershop.app.service.impl;

import com.computershop.app.model.Order;
import com.computershop.app.model.OrderProduct;
import com.computershop.app.model.dto.OrderDTO;
import com.computershop.app.model.dto.OrderProductDTO;
import com.computershop.app.model.dto.request.OrderRequest;
import com.computershop.app.repository.OrderProductRepository;
import com.computershop.app.repository.OrderRepositoy;
import jakarta.validation.Valid;
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

    public OrderProduct fromDTO(@Valid OrderProductDTO orderProductDTO){
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setId(orderProductDTO.getId());
        orderProduct.setProduct(this.productService.fromRequest(orderProductDTO.getProductRequest()));
        orderProduct.setOrder(this.orderService.fromRequest(orderProductDTO.getOrderRequest()));

        return orderProduct;
    }
}
