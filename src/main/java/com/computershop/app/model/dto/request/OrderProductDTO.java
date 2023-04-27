package com.computershop.app.model.dto.request;

import com.computershop.app.model.Order;
import com.computershop.app.model.OrderProduct;
import com.computershop.app.model.Product;
import jakarta.validation.constraints.NotNull;

public class OrderProductDTO {
    @NotNull(message = "Invalid Input!")
    private Long orderId;
    @NotNull(message = "Invalid Input!")
    private Long productId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public OrderProduct toEntity(){
        Order order = new Order(this.orderId);
        Product product = new Product(this.productId);
        return new OrderProduct(order, product);
    }
}
