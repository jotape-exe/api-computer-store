package com.computershop.app.model.dto;

import com.computershop.app.model.dto.request.OrderRequest;
import com.computershop.app.model.dto.request.ProductRequest;
import jakarta.validation.constraints.NotNull;

public class OrderProductDTO {

    private Long id;

    @NotNull
    private OrderRequest orderRequest;

    @NotNull
    private ProductRequest productRequest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderRequest getOrderRequest() {
        return orderRequest;
    }

    public void setOrderRequest(OrderRequest orderRequest) {
        this.orderRequest = orderRequest;
    }

    public ProductRequest getProductRequest() {
        return productRequest;
    }

    public void setProductRequest(ProductRequest productRequest) {
        this.productRequest = productRequest;
    }
}
