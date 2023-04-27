package com.computershop.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order_product")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "id_order", referencedColumnName = "id")
    @ManyToOne
    private Order order;

    @JoinColumn(name = "id_product", referencedColumnName = "id")
    @ManyToOne
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public OrderProduct() {
    }

    public OrderProduct(Long id, Order order, Product product) {
        this.id = id;
        this.order = order;
        this.product = product;
    }

    public OrderProduct(Order order, Product product) {
        this.order = order;
        this.product = product;
    }


}
