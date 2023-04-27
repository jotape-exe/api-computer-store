package com.computershop.app.model.dto.response;

import com.computershop.app.model.Order;
import com.computershop.app.model.OrderProduct;
import com.computershop.app.model.Product;

public class OrderProductView {
    private Order order;
    private Product product;

    public OrderProductView(Order order, Product product) {
        this.order = order;
        this.product = product;
    }

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
    public OrderProductView(OrderProduct orderProduct){
        this.order = orderProduct.getOrder();
        this.product = orderProduct.getProduct();
    }
}
