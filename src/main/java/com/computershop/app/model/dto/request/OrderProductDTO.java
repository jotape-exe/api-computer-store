package com.computershop.app.model.dto;

import com.computershop.app.model.Order;
import com.computershop.app.model.OrderProduct;
import com.computershop.app.model.Product;
import jakarta.validation.constraints.NotNull;

public class OrderProductDTO {
        @NotNull
    private Order order;

    @NotNull
    private Product product;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderProduct toEntity(){
        return new OrderProduct(this.order, this.product);
    }
}
